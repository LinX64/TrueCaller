## Solution

This is a solution to the task given by True Caller..

## Technology Used

- MVVM
- Retrofit
- Mockito
- JUnit
- Hilt-dagger

# Solution

Before starting, i used Async along with await() to get the data from the API with three different
requests as needed.

- Data is being handled by the Repository class. Logic is being handled by the ViewModel class.

The first question; I used a function for splitting the body tagto split the <body tag, and then
checking the end of the tag by [0]. After that, I used [10] to get the first 10 character of the
string.

The second question; I used a function for splitting the body tag, just like the previous method,
and using a loop to start from 10 until the end of the body lenght and added step for 10 to go by 10
steps. At the end, I used append() to add the string to the StringBuilder.

The third question; I used the same function for spliiting the body tag, and also used regex to
split the string by the space, and then used a loop to check the string if it containts the word and
then added it to the list.