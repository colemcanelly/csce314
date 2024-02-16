-- base case
-- recursive (function calls and concatenation) f(ys) + [x] + f(zs)
--  ys = { a : a ∈ xs ∧ a ≤ x }
--  zs = { b : b ∈ xs ∧ b > x }
-- f :: Ord a => [a] -> [a]
quicksort [] = []
quicksort (pivot:list) = quicksort lt ++ [pivot] ++ quicksort gt
  where
    lt = [a | a <- list, a <= pivot]
    gt = [b | b <- list, b > pivot]

