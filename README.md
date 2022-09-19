## Task - Making three requests simultaneously 

## Teck stack and Technologies

- MVVM
- LiveData
- Retrofit
- Mockito
- JUnit
- Hilt-dagger
- Enum class
- Sealed class

# Preview

<img src="https://i.imgur.com/xwdRtNc.gif " width="200" />

# Solution

I used `Async()` along with `await()` to get the data from the API with three different requests simultaneously as requested. Data is being handled by the Repository. Logic is being handled by the ViewModel.

1. In The first question; I used a function for splitting the split the `<body` tag, and then
checked the end of the tag by [0]. After that, I used [10] to get the first 10 characters of the
string.

2. In The second question; I used a function for splitting the `<body` tag, just like the previous method,
and using a loop to start from 10 until the end of the body length and added 10 steps to go by 10
steps. At the end, I used `append()` to add the string to the `StringBuilder`.

3. For The third question; I used the same function for splitting the `<body` tag, and also used regex (`\\s+`) to
split the string by the space, and then used a loop to check the string if it contains the word and
then added it to the list.
