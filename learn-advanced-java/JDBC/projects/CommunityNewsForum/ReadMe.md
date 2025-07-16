# Community News Forum
Welcome to Community News Forum, your personal news network created by the community, for the community! (Also, your employer)

As you are rolling around your apartment finishing up your morning routine, you get a call from one of the managers on the back-end development team.

“Good morning, we are in the final stages of pre-launch preparation, and one of our back-end software engineers has lost his login credentials and can’t get logged into our workspace today to finish up the connections to our SQLite database.”

You’re puzzled as to what this has to do with you, you’ve been working diligently on the front-end for the past year as part of the UX team.

They continue, “We know you have mostly been working on the front end of the application, but we need you to shift focus today and help us get his work done before the launch! You’ll need to brush up on your JDBC and SQL on the way to the office this morning.”

You hastily agree! An opportunity to broaden your experience and be a part of the whole user experience, from start to finish! You wrap up the morning chores and head to work, excited to start the day.

## Database Structure
### POSTS Table

| Column 1	| Column 2	| Column 3	| Column 4	| Column 5 |
| --------  | --------  | --------- | ---------- | -------- |
| Name: ID  | Name: TITLE | Name: MESSAGE | Name: USER_ID | Name: POST_DATE |
| Data Type: TEXT | Data Type: TEXT | Data Type: TEXT | Data Type: TEXT | Data Type: TEXT |
| Index: 1  | Index: 2	| Index: 3	| Index: 4 | Index: 5 |

### COMMENTS Table

| Column 1	| Column 2	| Column 3	| Column 4 |
| Name: POST_ID | Name: USER_ID | Name: COMMENT_DATE | Name: COMMENT |
| Data Type: TEXT | Data Type: TEXT | Data Type: TEXT | Data Type: TEXT |
| Index: 1	| Index: 2	| Index: 3	| Index: 4 |

### USERS Table

| Column 1 | Column 2 | Column 3 |
| Name: ID | Name: USERNAME | Name: PASSWORD |
| Data Type: TEXT | Data Type: TEXT | Data Type: TEXT |
| Index: 1 | Index: 2	| Index: 3 |



