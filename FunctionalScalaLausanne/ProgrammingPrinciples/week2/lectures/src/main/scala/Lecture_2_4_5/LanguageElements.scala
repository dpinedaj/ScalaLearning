package Lecture_2_4_5

object LanguageElements {

  /*
   Types:
      Can be SympleType | FunctionType

      FunctionType =  SimpleType  =>  Type
            |  ( [Types] )  =>  Type

      SympleType = Ident
         Types = Type { ',' Type }


     a type can be:
      Numeric type: Int, Double (and Byte, Short, Char, Long, FLoat),
      Boolean type: true, false
      String Type
      Function type: like Int => Int, (Int, Int) => Int

   */


  /*
  Expressions:
    Can be:
      Identifier: such as x, isGoodEnough,
      Literal: like 0, 1.0, "abc",
      Function application: like sqrt(x),
      Operator application: like -x, y + x,
      Selection: like math.abs,
      Conditional expression: like if (x<0) -x else x,
      Block: like {val x=math.abs(y); x*2}
      Anonymous function: like x => x+1

   */

  /*
  Definitions:
    Can be:
      Function definition: like def square(x: Int) = x*x
      Value definition: like val y = square(2)

  Parameters:
    Can be:
      call-by-value parameter: like (x: Int),
      call-by-name parameter: like (y: => Double)
   */

  /*
  Precedences:
  ( (a+b) ^? (c ?^ d) ) less ( (a==>b) | c )
   */
}
