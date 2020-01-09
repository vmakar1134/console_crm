# console_crm
A simple java project with the console interface for university, which consists of departments and lectors
Create a simple java project with the console interface for university, which consists of departments and lectors. The lectors could work in more than one department. A lector could have one degree (assistant, associate professor, professor).

All data is stored in the relational database.  

Please send us a link to the GitHub repository with your version of this project.

The app should implement such commands:
1.Who is head of department {department_name}
Answer: Head of {department_name} department is {head_of_department_name}
2.Show {department_name} statistic.
	Answer: assistans - {assistams_count}. 
  associate professors - {associate_professors_count}
  professors -{professors_count}
3. Show the average salary for department {department_name}.
	Answer: The average salary of {department_name} is {average_salary}
4. Show count of employee for {department_name}.
	Answer: {employee_count}
5. Global search by {template}.   
	Example: Global search by van
	Answer: Ivan Petrenko, Petro Ivanov

There is a sql script inside /src folder, so you can simply generate database for this project.

