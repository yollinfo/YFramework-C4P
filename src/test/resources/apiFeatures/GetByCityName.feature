Feature: Get weather by city name 


@test1  @ApiRegression
Scenario Outline: Post Once  validate multiple times 
	Given User gets weather for "Washington DC" city when flag is "<flags>" 
	Then User validates response with "<jsonPath>" 
	
	
	Examples: 
		|flags         |jsonPath                           |        
		|true          |$.weather[0].description           |
		|false         |$.weather[0].id                    |
		|false         |$.weather[0].main                  |
		|false         |$.weather[0].icon                  |
		#
		|false         |$.coord.lon                        |
		|false         |$.coord.lat                        |
		#
		|false         |$.base                             | 
		
		
		@byCityName @ct1  @ApiRegression
		Scenario Outline: Positive test 
			Given User gets weather for "Washington DC" city 
			Then User validates "<element>" in response with "<jsonPath>" 
			Examples: 
				|element       |jsonPath 				  |
				|description   | $.weather[0].description |
				|id 		   | $.weather[0].id		  |
				|main  		   | $.weather[0].main		  |
				|icon  	       | $.weather[0].icon 		  |
				|lon          | $.coord.lon              |
				|lat          | $.coord.lat              |
				|base          | $.base              |
				
				
				
				@byCityId 
				Scenario Outline: get weather ByCity ID 
					Given User gets weather for "Washington DC" city 
					Given User gets weather for "4140963" id 
					Then User compares "<element>" in by response with "<jsonPath>" in ByCityID response 
					Examples: zdd
						|element       |jsonPath 				  |
						|description   | $.weather[0].description |
						|id 		   | $.weather[0].id		  |
						|main  		   | $.weather[0].main		  |
						|icon  	       | $.weather[0].icon 		  |
						|lon          | $.coord.lon              |
						|lat          | $.coord.lat              |
						|base          | $.base              |		