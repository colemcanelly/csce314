gcd1 a 0 = a
gcd1 a b = gcd1 b (a `rem` b)