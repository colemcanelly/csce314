-- Problem 1 & 2

module Stack (StkType, push, pop, top, empty) where

newtype StkType a  = Stk [a]  deriving Show  -- line (1)

push :: a -> StkType a -> StkType a
push x (Stk xs)  = Stk (x:xs)
pop :: StkType a -> StkType a
pop (Stk (_:xs)) = Stk xs   -- line (2)
top :: StkType a -> a
top (Stk (x:_))  = x        -- line (3)
empty :: StkType a
empty            = Stk []

stack1 :: StkType Int
stack1 = push (3::Int) . push 4 . push 5 $ empty
stack2 :: StkType Int
stack2 = pop stack1

-- Problem 1
{-
type of Stk[4,2,5,1]    ⇒ Num a => StkType a
type of Stk in line (1) ⇒ [a] -> StkType a
type of (push 'a')      ⇒ StkType Char -> StkType Char
type of pop in line (2) ⇒ StkType a -> StkType a
type of stack1          ⇒ StkType Int
type of top in line (3) ⇒ StkType a -> a
-}

--  Problem 2
{-
Result of `top stack1`  ⇒ 3
Result of `top stack2`  ⇒ 4
Value of stack1         ⇒ Stk [3,4,5]
Value of stack2         ⇒ Stk [4,5]
-}