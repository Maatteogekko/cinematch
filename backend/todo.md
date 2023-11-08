# UX
- users can create watch session shareable via code
- after joining, list of same 50 movies but in different order
- swipe left/right to dislike/like a movie
- when there's a match, notify the users
- if no match, users have to re-create the session // why not just regenerate the movie pool?

# tasks
- user authentication and session managment
- endpoints for creating and joining watch sessions
- endpoints for liking and disliking movies
- database for session data; user choices
- http calls to <a href="https://developer.themoviedb.org/reference">themoivebd</a> for generating movie pool
- matching logic to find shared likes
**suggested deadline: *2023-12-03***

# to-do-list
- [ ] user authentication, session managment
- [ ] endpoints for creating and joining sessions
- [ ] endpoints for liking and disliking movies
- [ ] database for session data; user choices 
- [ ] http calls to <a href="https://developer.themoviedb.org/reference">themoivebd</a> for generating movie pool
	- [x] http get request
	- [x] api key
		 - dc7d794112655ff472cde48b94bab9bb
	- [ ] generating movie pool
- [ ] matching logic to find shared likes

# currently working on:
- endpoitns for liking and disliking movies
- sql database
