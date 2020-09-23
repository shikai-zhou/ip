# User Guide

## Features 

### Add task
Add a task into the list

### Delete task
remove a task from the list

### Mark Done
mark a task as completed

### Find 
Find a regular expression int the task list.

### Time
Stores the user entered date as Java LocalDate
## Usage

### `todo` - Add a new todo task into our list

Describe action and its outcome.

Example of usage: 

`todo DESCRIPTION`

Expected outcome:

`Got it. I've added this task: `

`[T][N] DESCRIPTION`

`Now you have NUMBER task(s) in the list.`
### `event` - Add a new todo task into our list

Describe action and its outcome.

Example of usage: 

`Got it. I've added this task: `

`event DESCRIPTION /at TIME`

`Now you have NUMBER task(s) in the list.`
Expected outcome:

`Got it. I've added this task: `

`[E][N] DESCRIPTION (at: TIME)`

`Now you have NUMBER task(s) in the list.`
### `deadline` - Add a new todo task into our list

Describe action and its outcome.

Example of usage: 

`deadline DESCRIPTION /by TIME`

Expected outcome:

`[D][N] DESCRIPTION (by: TIME)`
### `list` - List all entries in our Task List
Example of usage: 

`list`

Expected outcome:

`Here are the tasks in your list:`

`1.[T][✓] borrow book`

`2.[E][✗] project meeting (at: Mon 2-4pm)`

`3.[D][✗] do homework (by: no idea :-p)`

### `done` - Mark a task as completed

Example of usage: 

`done INDEX`

Expected outcome:

`[T][Y] DESCRIPTION`
### `delete` - Delete a task from list

Example of usage: 

`delete INDEX`

Expected outcome:

`Noted. I've removed this task: `

`[E][Y] project meeting (at: Mon 2-4pm)`

### `find` - Find a task in the list that matches a keyword

Example of usage: 

`find KEYWORD`

Expected outcome:

`[T][N] keyword`
