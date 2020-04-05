Given path '/products/'+product.id
When method GET
Then status 200
And match $ contains {id:'#(product.id)',name:'#(product.name)',type:'#(product.type)',price:#(product.price)}