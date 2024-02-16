--  Problem 3 & 4
module Stack (StkType, push, pop, top, empty) where

data StkType a = EmptyStk | Stk a (StkType a) deriving Show  -- line (10)

push x s = Stk x s
pop (Stk _ s) = s      -- line (11)
top (Stk x _) = x      -- line (12)
empty = EmptyStk

-- how to construct objects stack1 and stack2 is exactly the same as before
-- because the interface is the same
stack1 = push (3 :: Int) . push 4 . push 5 $ empty
stack2 = pop stack1

--  Problem 3
{-
type of `top` in line 12    ⇒ StkType a -> a
type of `pop` in line 11    ⇒ StkType a -> StkType a
value of stack1             ⇒ Stk 3 (Stk 4 (Stk 5 EmptyStk))
value of stack2             ⇒ Stk 4 (Stk 5 EmptyStk)
type of Stk in line 10      ⇒ a -> StkType a -> StkType a
type of `push 'a'`          ⇒ StkType Char -> StkType Char
-}

--  Problem 4
stack3 = Stk 6 (Stk 2 (Stk 5 EmptyStk))