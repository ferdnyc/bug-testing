####################### CMakeLists.txt (libopenshot) #########################
# @brief CMake build file for libopenshot (used to generate Python SWIG bindings)
# @author Jonathan Thomas <jonathan@openshot.org>
#
# @section LICENSE
#
# Copyright (c) 2008-2019 OpenShot Studios, LLC
# <http://www.openshotstudios.com/>. This file is part of
# OpenShot Library (libopenshot), an open-source project dedicated to
# delivering high quality video editing and animation solutions to the
# world. For more information visit <http://www.openshot.org/>.
#
# OpenShot Library (libopenshot) is free software: you can redistribute it
# and/or modify it under the terms of the GNU Lesser General Public License
# as published by the Free Software Foundation, either version 3 of the
# License, or (at your option) any later version.
#
# OpenShot Library (libopenshot) is distributed in the hope that it will be
# useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
# GNU Lesser General Public License for more details.
#
# You should have received a copy of the GNU Lesser General Public License
# along with OpenShot Library. If not, see <http://www.gnu.org/licenses/>.
################################################################################
include(GNUInstallDirs)

### Enable some legacy SWIG behaviors, in newer CMAKEs
if (POLICY CMP0078)
  cmake_policy(SET CMP0078 NEW)
endif()
if (POLICY CMP0086)
  cmake_policy(SET CMP0086 OLD)
endif()

find_package(PythonInterp 3)
find_package(PythonLibs 3)

############### SWIG PYTHON BINDINGS ################
find_package(SWIG 3.0 REQUIRED)
include(${SWIG_USE_FILE})


if (NOT PYTHONLIBS_FOUND OR NOT PYTHONINTERP_FOUND)
  return()
endif()

### Include Python header files
include_directories(${PYTHON_INCLUDE_PATH})

if (CMAKE_VERSION VERSION_LESS 3.12)
  ### Include project headers
  include_directories(
    "${PROJECT_SOURCE_DIR}/src"
    "${PROJECT_BINARY_DIR}/src")
endif()

### Enable C++ in SWIG
set_property(SOURCE openshot.i PROPERTY CPLUSPLUS ON)
set_property(SOURCE openshot.i PROPERTY SWIG_MODULE_NAME openshot)

### Suppress a ton of warnings in the generated SWIG C++ code
set(SWIG_CXX_FLAGS "-Wno-unused-variable -Wno-unused-function \
  -Wno-deprecated-copy -Wno-class-memaccess -Wno-cast-function-type \
  -Wno-unused-parameter -Wno-catch-value -Wno-sign-compare -Wno-ignored-qualifiers")
separate_arguments(sw_flags UNIX_COMMAND ${SWIG_CXX_FLAGS})
set_property(SOURCE openshot.i PROPERTY GENERATED_COMPILE_OPTIONS ${sw_flags})

### Take include dirs from target, automatically if possible
if (CMAKE_VERSION VERSION_GREATER 3.13)
  set_property(SOURCE openshot.i PROPERTY USE_TARGET_INCLUDE_DIRECTORIES True)
elseif (CMAKE_VERSION VERSION_GREATER 3.12)
  set_property(SOURCE openshot.i PROPERTY
    INCLUDE_DIRECTORIES $<TARGET_PROPERTY:openshot,INCLUDE_DIRECTORIES>)
endif ()

### Add the SWIG interface file (which defines all the SWIG methods)
if (CMAKE_VERSION VERSION_LESS 3.8.0)
  swig_add_module(pyopenshot python openshot.i)
else()
  swig_add_library(pyopenshot LANGUAGE python SOURCES openshot.i)
endif()

### Set output name of target
set_target_properties(pyopenshot PROPERTIES
PREFIX "_" OUTPUT_NAME "openshot")

### Link the new python wrapper library with libopenshot
target_link_libraries(pyopenshot PUBLIC
  ${PYTHON_LIBRARIES} OpenShot::Audio OpenShot::Lib)
### Use RPATH to locate bundled libs
set_target_properties(pyopenshot PROPERTIES
  INSTALL_RPATH "$ORIGIN"
  INSTALL_RPATH_USE_LINK_PATH TRUE
  BUILD_RPATH_USE_ORIGIN TRUE
  )
