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
cmake_minimum_required(VERSION 3.12)
project(OpenShotBindings LANGUAGES C CXX)

include(GNUInstallDirs)

###
### BUILD PATH
###
set(CMAKE_LIBRARY_OUTPUT_DIRECTORY "${CMAKE_CURRENT_BINARY_DIR}/python/libopenshot" CACHE PATH "Common build-tree location for library objects" FORCE)
set(CMAKE_RUNTIME_OUTPUT_DIRECTORY "${CMAKE_CURRENT_BINARY_DIR}/python/libopenshot" CACHE PATH "Common build-tree location for executable objects" FORCE)

add_subdirectory(libopenshot-audio)
add_subdirectory(libopenshot)

#set(CMAKE_SWIG_FLAGS $<TARGET_PROPERTY:OpenShot::Lib,COMPILE_DEFINITIONS>)

add_subdirectory(python/libopenshot)

###
### Create setuptools configuration
###
configure_file(python/setup.cfg.in python/setup.cfg)
configure_file(python/setup.py.in python/setup.py)

######### INSTALL PATH ########
set (PYTHON_MODULE_PATH "libopenshot")
message(STATUS "PYTHON_MODULE_PATH: ${CMAKE_INSTALL_PREFIX}/${PYTHON_MODULE_PATH}")

############### INSTALL HEADERS & LIBRARY ################
### Install Python bindings
install(TARGETS pyopenshot
  DESTINATION ${PYTHON_MODULE_PATH} )
install(FILES ${CMAKE_CURRENT_BINARY_DIR}/python/libopenshot/openshot.py
  DESTINATION ${PYTHON_MODULE_PATH} )
install(FILES
  ${CMAKE_CURRENT_BINARY_DIR}/python/setup.cfg
  ${CMAKE_CURRENT_BINARY_DIR}/python/setup.py
  DESTINATION ".")

