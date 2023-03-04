# Assignment
An assignment based on CRUD api using spring boot.

Create a the backend for a Task Manager WebApp which has the following User Stories
implemented:
User Story 1 - Create Task
An API to create a task in the database. Data points to be taken as input are as follows:
● Task Title
● Task ETA (Tentative date of completion)
○ If user doesn’t input value, default to 1 week from creation date
○ Format: dd/mm/yyyy
● Task Status (Fixed Options: Pending, In Progress, In Review and Complete)
○ If user doesn’t input value, default to be Pending
User Story 2 - Retrieve Task
An API to retrieve task details upon providing Task ID as input. Following details to be
output:
● Task Title
● Task ETA
● Task Status
User Story 3 - Retrieve All Tasks
An API to retrieve task details of all tasks. Following details to be output:
● Task ID
● Task Title
● Task ETA
● Task Status
User Story 4 - Update Task
An API to update any of the following attributes of the task by providing task ID as input:
● Task Title
● Task ETA
● Task Status
User Story 5 - Audit Trail
An API to provide details of all updates made to a task with task ID as input. Following
details to be provided:
● Task Title
● Updated Timestamp
● Updated Fields
● Old Value
● New Value
User Story 6 - Delete Task
An API to delete a task with Task ID as input.

