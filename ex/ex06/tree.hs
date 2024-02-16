--  Problem 6, 7, & 8
data Tree a = Leaf a | Node a (Tree a) (Tree a)

tfold :: t -> (a -> t -> t -> t) -> Tree a -> t
tfold f g (Leaf a) = f
tfold f g (Node x left right) = g x (tfold f g left) (tfold f g right)

tree1 = Node (2 :: Int) (Node 3 (Leaf 2) (Leaf 4)) (Node 2 (Leaf 4) (Leaf 3))

--  Problem 6
{-
type of `top` in line 12    ⇒ StkType a -> a


type of `Leaf 8`                ⇒ Num a => Tree a
type of `Node True(Leaf False)` ⇒ Tree Bool -> Tree Bool
type of `tree1`                 ⇒ Tree Int
type of `Node 'A'`              ⇒ Tree Char -> Tree Char -> Tree Char
-}

--  Problem 7
{-
Evaluate: tfold 1 (\x y z -> x + y + z) tree1 ⇒ 11
-}

--  Problem 8
{-
Evaluate: tfold 0 (\_ y z -> 1 + y + z) tree1 ⇒ 3
-}