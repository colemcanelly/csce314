--  CSCE 314 Programming Languages
--  Exercise 5
--  Due: Wednesday, 2/22/2023
--  =====
--  Name: Cole McAnelly

-- Problem 1
data Shape = Circle Float
           | Rect Float Float
  deriving Show

area :: Shape -> Float
area (Circle r) = pi * r^2
area (Rect x y) = x * y

c1 :: Shape
c1 = Circle 2.7

c2 :: Shape
c2 = Circle 3.51

r1 :: Shape
r1 = Rect 3 4.19

r2 :: Shape
r2 = Rect 7.8 2.6

shapes :: [Shape]
shapes = [c1,c2,r1,r2]

--  Problem 1.3:
{-
    Define a function totalOfSomeArea using sum, filter, 
    and map that, given a list of Shapes, first, calculate 
    the area of each shape, and then, sum only the areas which 
    are within the range of [20,30]. Use a lambda expression 
    for the predicate for filter.
-}

totalOfSomeArea = sum . filter (\x -> x >= 20 && x <= 30) . map area


--  Problem 2
type Name = String
type Age = Int
data Person = Person Name Age  deriving Show

--  2.1
findPersonByAge :: (Age -> Bool) -> [Person] -> Maybe Person
--  2.2
findPersonByAge _ [] = Nothing
--  2.3
findPersonByAge pred (p@(Person _ a) : ps)
    | pred a = Just p
    | otherwise = findPersonByAge pred (tail ps)

--  findPersonByAge (< 20) persons

--  2.4
fromJust :: Maybe Person -> Person
fromJust (Just p) = p
fromJust Nothing = Person [] (-1)

persons :: [Person]
persons = [Person "Tom" 21, Person "Sam" 18, Person "Bob" 15]