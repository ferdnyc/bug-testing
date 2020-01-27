// CHECKSTYLE:OFF
package com.zaxxer.sparsebits;

/*- This software is the work of Paladin Software International, Incorporated,
 *  based upon previous work done for and by Sun Microsystems, Inc. */

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 *  This class implements a set of bits that grows as needed. Each bit of the
 *  bit set represents a <code>boolean</code> value. The values of a
 *  <code>SparseBitSet</code> are indexed by non-negative integers.
 *  Individual indexed values may be examined, set, cleared, or modified by
 *  logical operations. One <code>SparseBitSet</code> or logical value may be
 *  used to modify the contents of (another) <code>SparseBitSet</code> through
 *  logical <b>AND</b>, logical <b>inclusive OR</b>, logical <b>exclusive
 *  OR</b>, and <b>And NOT</b> operations over all or part of the bit sets.
 *  <p>
 *  All values in a bit set initially have the value <code>false</code>.
 *  <p>
 *  Every bit set has a current size, which is the number of bits of space
 *  <i>nominally</i> in use by the bit set from the first set bit to just after
 *  the last set bit. The length of the bit set effectively tells the position
 *  available after the last bit of the SparseBitSet.
 *  <p>
 *  The maximum cardinality of a <code>SparseBitSet</code> is
 *  <code>Integer.MAX_VALUE</code>, which means the bits of a
 *  <code>SparseBitSet</code> are labelled <code>
 *  0</code>&nbsp;..&nbsp;<code>Integer.MAX_VALUE&nbsp;&minus;&nbsp;1</code>.
 *  After the last set bit of a <code>SparseBitSet</code>, any attempt to find
 *  a subsequent bit (<i>nextSetBit</i>()), will return an value of &minus;1.
 *  If an attempt is made to use <i>nextClearBit</i>(), and all the bits are
 *  set from the starting position of the search to the bit labelled
 *  <code>Integer.MAX_VALUE&nbsp;&minus;&nbsp;1</code>, then similarly &minus;1
 *  will be returned.
 *  <p>
 *  Unless otherwise noted, passing a null parameter to any of the methods in
 *  a <code>SparseBitSet</code> will result in a
 *  <code>NullPointerException</code>.
 *  <p>
 *  A <code>SparseBitSet</code> is not safe for multi-threaded use without
 *  external synchronization.
 *
 * @author      Bruce K. Haddon
 * @author      Arthur van Hoff
 * @author      Michael McCloskey
 * @author      Martin Buchholz
 * @version     1.0, 2009-03-17
 * @since       1.6
 */
public class SparseBitSet implements Cloneable, Serializable
{
/* REMAINING SOURCE TRUNCATED AS NOT RELEVANT TO SYNTAX-HIGHLIGHTING EXAMPLE */
}
