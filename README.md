# Twitter Feed Example

Example of a twitter timeline app using MVP pattern and Twitter SDK for the service requests.

Features:
- Can filter between the tweets that are loaded in memory
- Will load more results while you scroll down

### NOTES
- Due to the time constraint that this little example had dependecy injection with dagger was not implemented, nevertheless everything was thought to
be implemented with dependency injection. This is why you will find the creation of the presenter and interactors burned in the on resume of the fragment
or in the constructor of the presenter.

- Something else to note is that there was a requirement that suggested that the search should also filter by users not only the text of the tweets.
This was not done because we were pulling the statuses for 1 single user as the requirement said, so if we filtered by user we would only be able to
search one single user even though it would have been pretty simple to implemented by creating an or inside this guava predicate:

`new Predicate<Tweet>() {
      @Override
      public boolean apply(Tweet input) {
          return input.text.toLowerCase().contains(newText.toLowerCase());
      }
  }`
