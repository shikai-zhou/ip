# User Guide

## Features 

### Add task
Let us add a task into our reminder list. There are three types of tasks:
1. Todo
2. Deadline
3. Event

Todo is a simple reminder while deadline and event can help you keep track of the time.

By default a new task added is uncompleted and is marked by `[N]`

### Delete task
Remove a task from our reminder list.

### Mark Done
Mark a task in our list as completed. A completed task is marked as `[Y]`

### Find 
Find a regular expression in the task list.

### Date
Allows the user to enter a date and parses it into a LocalDate.

### Help
Display help options.
## Usage

### `todo` - Add a new todo task into our list

Add a simple todo task into our list.

Example of usage: 

`todo DESCRIPTION`

Expected outcome:

`Got it. I've added this task: `

`[T][N] DESCRIPTION`

`Now you have NUMBER task(s) in the list.`
### `event` - Add a new event task into our list

Add an event task and the time when the task occurs. The time format should be DD-MM-YYYY.

Example of usage: 

`event DESCRIPTION /at TIME`

Expected outcome:

`Got it. I've added this task: `

`[E][N] DESCRIPTION (at: TIME)`

`Now you have NUMBER task(s) in the list.`
### `deadline` - Add a new deadline task into our list

Add a task with a deadline and the time of the deadline. The time format should be DD-MM-YYYY.
Example of usage: 

`deadline DESCRIPTION /by TIME`

Expected outcome:

`Got it. I've added this task: `

`[D][N] DESCRIPTION (by: TIME)`

`Now you have NUMBER task(s) in the list.`

### `list` - List all entries in our Task List
Example of usage: 

`list`

Expected outcome:

`Here are the tasks in your list:`

`1.[T][Y] borrow book`

`2.[E][N] project meeting (at: Mon 2-4pm)`

`3.[D][N] do homework (by: no idea :-p)`

### `done` - Mark a task as completed

Example of usage: 

`done INDEX`

Expected outcome:


`Nice! I've marked this task as done: `

`[T][Y] DESCRIPTION`
### `delete` - Delete a task from list

Example of usage: 

`delete INDEX`

Expected outcome:

`Noted. I've removed this task: `

`[E][Y] project meeting (at: Mon 2-4pm)`

`Now you have NUMBER task(s) in the list.`

### `find` - Find a task in the list that matches a keyword
A simple find function that lets you find tasks which have descriptions that matches your keyword. Find is case-sensitive.

Example of usage: 

`find KEYWORD`

Expected outcome:

`INDEX.[T][N] keyword`

### `help` - Display help options
Example of usage:

`help`

Expected outcome:

`Here are all the valid commands:`

`   todo DESCRIPTION`

`   event DESCRIPTION /at TIME`

`   deadline DESCRIPTION /by TIME`

`   list`

`   done INDEX`

`   find KEYWORD`

`   bye`

### `bye` - Exits the program

Quits the program after saving the current list in a .txt file

Example of usage:

`bye`

Expected outcome:

`Bye. Hope to see you again soon!`
