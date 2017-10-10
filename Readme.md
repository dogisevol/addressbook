FORMAT: 1A
HOST:

# Application 

Cashman is a simple API allowing to initialize and update atm state

## Build
	mvn package

## Start server
	java -jar target/cashman-0.0.1-SNAPSHOT.jar
	or
	mvn spring-boot:start
	
## Other
	H2 console is available on http://localhost:8080/h2-console/	
	JDBC URL: jdbc:h2:mem:contact

## API	

### contacts Collection [/contact]

#### List All contacts [GET]

+ Response 200 (application/json)

		[
			{
			   "serialNumber":"my contact",
			   "businessAddresses":[
				  {
					 "address":5.00,
					 "count":100,
					 "id":5
				  },
				  {
					 "address":100.00,
					 "count":100,
					 "id":2
				  }
			   ],
			   "personalAddresses":[
				  {
					 "address":0.50,
					 "count":100,
					 "id":7
				  },
				  {
					 "address":0.10,
					 "count":100,
					 "id":6
				  }
			   ]
			}
		]
		
#### Initialize a new contact [POST]

+ Request (application/json)

		{
		   "serialNumber":"myNumber",
		   "businessAddresses":[
			  {
				 "address":5.00,
				 "count":100
			  },
			  {
				 "address":100.00,
				 "count":100
			  }
		   ],
		   "personalAddresses":[
			  {
				 "address":0.50,
				 "count":100
			  },
			  {
				 "address":0.10,
				 "count":100
			  }
		   ]
		}

+ Response 201 (application/json)

    + Headers

            Location: /questions/2

    + Body

			{
				"id": 103,
				"serialNumber": "myNumber",
				"businessAddresses":
				[
					{
						"address": 5,
						"count": 100,
						"id": 20
					},
					{
						"address": 100,
						"count": 100,
						"id": 21
					}
				],
				"personalAddresses":
				[
					{
						"address": 0.5,
						"count": 100,
						"id": 22
					},
					{
						"address": 0.1,
						"count": 100,
						"id": 23
					}
				],
				"errors":
				[
				]
			}

### contact [/contact/{id}]

#### Find by id [GET]

+ Request (application/json)

+ Response 404 (not found)

+ Response 200 (application/json)

    + Body   

			{
				"id": 103,
				"serialNumber": "myNumber",
				"businessAddresses":
				[
					{
						"address": 5,
						"count": 100,
						"id": 20
					},
					{
						"address": 100,
						"count": 100,
						"id": 21
					}
				],
				"personalAddresses":
				[
					{
						"address": 0.5,
						"count": 100,
						"id": 22
					},
					{
						"address": 0.1,
						"count": 100,
						"id": 23
					}
				],
				"errors":
				[
				]
			}
	
### contact [/withdraw/{id}/{withdrawAmount}]

#### Withdraw from atm [PUT]	

+ Response 404 (not found)

+ Response 200 (application/json)

    + Body 
	
			{
				"id": 103,
				"serialNumber": "myNumber",
				"businessAddresses":
				[
					{
						"address": 5,
						"count": 100,
						"id": 20
					},
					{
						"address": 100,
						"count": 100,
						"id": 21
					}
				],
				"personalAddresses":
				[
					{
						"address": 0.5,
						"count": 100,
						"id": 22
					},
					{
						"address": 0.1,
						"count": 100,
						"id": 23
					}
				],
				"errors":
				[
				]
			}
	
### contact [/addTo/{id}]

#### Add to atm [PUT]	

+ Request (application/json)

		{
		   "serialNumber":"myNumber",
		   "businessAddresses":[
			  {
				 "address":5.00,
				 "count":100
			  },
			  {
				 "address":100.00,
				 "count":100
			  }
		   ],
		   "personalAddresses":[
			  {
				 "address":0.50,
				 "count":100
			  },
			  {
				 "address":0.10,
				 "count":100
			  }
		   ]
		}

+ Response 404 (not found)

+ Response 200 (application/json)	

		{
			"id": 103,
			"serialNumber": "myNumber",
			"businessAddresses":
			[
				{
					"address": 5,
					"count": 200,
					"id": 20
				},
				{
					"address": 100,
					"count": 200,
					"id": 21
				}
			],
			"personalAddresses":
			[
				{
					"address": 0.5,
					"count": 200,
					"id": 22
				},
				{
					"address": 0.1,
					"count": 200,
					"id": 23
				}
			],
			"errors":
			[
			]
		}

	
## TODO	

+ Branch and Bound Knapsack (knapsack is used for non-canonical monetary systems for which non greedy algorythm doesn't work correctly)

+ Error handling 

+ Cover all controller methods with tests

+ Find more counterexamples