
//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
/** @author  John Miller
 *  @version 1.2
 *  @date    Sun Aug 23 15:42:06 EDT 2015
 *  @see     LICENSE (MIT style license file).
 */

package scalation.linalgebra

import reflect.ClassTag

import scalation.math.StrO._

//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
/** The `Vec` trait establishes a common base type for all vectors (e.g., VectorD).
 */
trait Vec
{
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    /** Return the size (number of elements) of the vector.
     */
    def size: Int

} // Vec trait


//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
/** The `PredicateType` object defines predicate and condition types for numeric
 *  datatypes.
 */
object PredicateType
{
    /** Unary predicate (Boolean function)
     */
//  type Predicate [U <: T <: Numeric [T]] = U => Boolean

    /** Tuple consisting of a column name and an applicable unary predicate
     */
    type Condition [T <: Numeric [T]] = Tuple2 [String, T => Boolean]

} // Predicate

import PredicateType._

//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
/** The `Vec` object provides a minimal set of functions that apply across all
 *  types of vectors. 
 *  @see `scalation.relalgebra.Relation`
 */
object Vec
{
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    /** Return the 'i'th element.
     *  @param  i  the index position 
     */
    def apply (x: Vec, i: Int): Any =
    {
        x match {
        case _: VectorD => x.asInstanceOf [VectorD] (i)
        case _: VectorS => x.asInstanceOf [VectorS] (i)
        case _  =>  println ("vector type not supported"); 0
        } // match
    } // apply

    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    /** Concatenate vectors 'x' and 'y'.
     *  @param x  the first vector
     *  @param y  the second vector
     */
    def ++ (x: Vec, y: Vec): Vec =
    {
        x match {
        case _: VectorD => x.asInstanceOf [VectorD] ++ y.asInstanceOf [VectorD]
        case _: VectorS => x.asInstanceOf [VectorS] ++ y.asInstanceOf [VectorS]
        case _  =>  println ("vector type not supported"); null
        } // match
    } // ++

    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    /** Copy of vector 'x' with scalar 's' appended.
     *  @param x  the vector
     *  @param y  the scalar to append
     */
    def :+ [T <: Any] (x: Vec, s: T): Vec =
    {
        s match {
        case _: Double => if (x == null) VectorD (s.asInstanceOf [Double])
                          else x.asInstanceOf [VectorD] ++ s.asInstanceOf [Double]
        case _: StrNum => if (x == null) VectorS (s.asInstanceOf [StrNum])
                          else x.asInstanceOf [VectorS] ++ s.asInstanceOf [StrNum]
        case _: String => if (x == null) VectorS (StrNum (s.asInstanceOf [String]))
                          else x.asInstanceOf [VectorS] ++ StrNum (s.asInstanceOf [String])
        case _  =>  println ("vector type not supported"); null
        } // match
    } // :+

    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    /** Filter vector 'x' based on predicate 'p', returning a new vector.
     *  @param x  the vector to filter
     *  @param p  the predicate (Boolean function) to apply
     */
    def filter [T: ClassTag: Numeric] (x: Vec, p: T => Boolean): Vec =
    {
        x match {
        case _: VectorD => x.asInstanceOf [VectorD].filter (p.asInstanceOf [Double => Boolean])
        case _: VectorS => x.asInstanceOf [VectorS].filter (p.asInstanceOf [StrNum => Boolean])
        case _  =>  println ("vector type not supported"); null
        } // match
    } // filter

    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    /** Filter vector 'x' based on predicate 'p', returning the positions in the vector.
     *  @param x  the vector to filter
     *  @param p  the predicate (Boolean function) to apply
     */
    def filterPos [T: ClassTag: Numeric] (x: Vec, p: T => Boolean): Array [Int] =
    {
        x match {
        case _: VectorD => x.asInstanceOf [VectorD].filterPos (p.asInstanceOf [Double => Boolean])
        case _: VectorS => x.asInstanceOf [VectorS].filterPos (p.asInstanceOf [StrNum => Boolean])
        case _  =>  println ("vector type not supported"); null
        } // match
    } // filterPos

    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    /** Select elements from vector 'x' at the given index positions.
     *  @param x    the vector to select from
     *  @param pos  the positions to select
     */
    def select (x: Vec, pos: Seq [Int]): Vec =
    {
        x match {
        case _: VectorD => x.asInstanceOf [VectorD].select (pos.toArray)
        case _: VectorS => x.asInstanceOf [VectorS].select (pos.toArray)
        case _  =>  println ("vector type not supported"); null
        } // match
    } // filterPos

} // Vec object

