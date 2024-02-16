# Haskell

Haskell seems to be a very math-oriented language, with much of the syntax representing abstract/discrete mathematics. Below are some math symbols that might be used to improve readability of haskell code.

| Symbol 	| Meaning               	| Symbol 	| Meaning                  	|
|:------:	|-----------------------	|:------:	|--------------------------	|
|    <   	| Less than             	|    >   	| Greater than             	|
|    ≤   	| Less than or equal to 	|    ≥   	| Greater than or equal to 	|
|    =   	| Equals                	|    ≠   	| Not equal                	|
|    ⇔   	| Equivalent            	|    ⇒   	| Implies                  	|
|    ∨   	| Or                    	|    ∧   	| And                      	|
|    ∴   	| Therefore             	|   { }  	| Set                      	|
|    ∪   	| Union                 	|    ∩   	| Intersection             	|
|    ∈   	| Element of            	|    ∉   	| Not element of           	|
|    ⊂   	| Subset                	|    ⊄   	| Not a subset             	|
|    ⊃   	| Superset              	|    ⊅   	| Not a superset           	|
|    ∀   	| For all               	|    ∃   	| There exists             	|

### Operators
- `++`  ⇒   Concatenate: `[1, 2] ++ [3, 4]` ⇒ `[1, 2, 3, 4]`
- `:`   ⇒   Prepended to: `3:[4, 6, 8]` ⇒ `[3, 4, 6, 8]`
- `<-`  ⇒   Belongs to (i.e. **∈**)
- `!!`  ⇒   Select element at: `[x, y, z] !! n`
- `map` ⇒  Higher order function, performs given function on all element: `map reverse ["dog","cat"]` ⇒ `["god","tac"]`
- `drop` ⇒  Remove the first n elements: `drop 2 [x,y,z]` ⇒ `[z]`
- `head` ⇒  Select the first element: `head [x,y,z]` ⇒ `x`
- `tail` ⇒  Remove the first element: `tail [x,y,z]` ⇒ `[y,z]`
- `take` ⇒  Select the first n elements: `take 2 [x,y,z]` ⇒ `[x,y]`
- `reverse` ⇒  Reverse a list: `reverse [2, 4, 6]` ⇒ `[6, 4, 2]`
- `length` ⇒  Return a length: `length [x,y,z]` ⇒ `3`
- `sum` ⇒  Calculate the sum of a list: `sum [x,y,z]` ⇒ `x+y+z`
- `product` ⇒  Calculate the product of a list: `product [x,y,z]` ⇒ `xyz`



