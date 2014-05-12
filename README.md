TweetMap
========

This is a web application which has tweets mapped to a google map based on the geographical location of the tweets. This has been developed using Java and deployed on Google AppEngine.

This application allows to search the tweets based on a certain keyword. A dropdown list is provided to allow the search by keyword. Memcache service from Google AppEngine has been used to reduce the calls to the datastore for queires which have already been done during a session.
