# Personal Finance Tracker

A web application for tracking personal finances, managing expenses, incomes, and budgets.

## Features

- **Expense Tracking:** Log your daily expenses, categorize them, and set descriptions.
- **Income Tracking:** Record various sources of income.
- **Budget Management:** Set budget limits for different categories.
- **Dashboard:** Visualize your financial data.
- **Reports:** Generate financial reports for analysis.

## Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/yourusername/finance-tracker.git
   ```

2. Navigate to the project directory:

   ```bash
   cd finance-tracker
   ```

3. Install the required dependencies:

   ```bash
   npm install
   ```

4. Start the application:

   ```bash
   npm start
   ```
## Documents

1. Trello: https://trello.com/b/DTZck8dS/personal-finance-tracker-webapp
2. PowerPoint: https://github.com/mohammedaladhary/Personal-Finance-Tracker-webApp/blob/main/Personal%20Finance%20Tracker.pdf
3. EndPoints: https://github.com/mohammedaladhary/Personal-Finance-Tracker-webApp/blob/main/API%20Endpoints.pdf
4. UML Digram: https://github.com/mohammedaladhary/Personal-Finance-Tracker-webApp/blob/main/UML%20Diagram.pdf

## Usage

1. Register for an account.
2. Log in to your account.
3. Add your expenses and incomes.
4. Set budget limits.
5. Explore the dashboard to view your financial data.
6. Generate financial reports.

## Contributing

We welcome contributions! If you'd like to contribute to this project, please follow these guidelines:

1. Fork the project.
2. Create a feature branch:

   ```bash
   git checkout -b feature/YourFeature
   ```

3. Commit your changes:

   ```bash
   git commit -m 'Add some feature'
   ```

4. Push to the branch:

   ```bash
   git push origin feature/YourFeature
   ```

5. Open a pull request.

## Technologies Used
- Java for the backend using Spring Boot.
- HTML, CSS, and JavaScript for the frontend.
- Spring Security for user authentication and authorization.
- MySQL or PostgreSQL for data storage.
- UML for system design.

## Controllers and Routes structure
- The relationships between the classes in your Personal Finance Tracker system. Here are the main relationships:
* 		User Class:
    * Has Many Expenses: A User can have multiple Expense records. This is a one-to-many relationship, indicating that a single user can record multiple expenses.
    * Has Many Incomes: Similarly, a User can have multiple Income records, representing various sources of income.
    * Generates Many Reports: Users can generate different financial reports, so there's a one-to-many relationship between User and Report.
* 		Expense Class:
    * Belongs to One User: An Expense record belongs to a single User, indicating a many-to-one relationship between Expense and User.
* 		Income Class:
    * Belongs to One User: Similarly, an Income record belongs to a single User, indicating a many-to-one relationship between Income and User.

## API EndPoints:
https://github.com/mohammedaladhary/Personal-Finance-Tracker-webApp/blob/main/API%20Endpoints.pdf

## Future Work

1. Implement Budget and Notification Section.
2. Developing a mobile app version of the Personal Finance Tracker to make it even more accessible to users on the go.
3. Explore the integration of the Personal Finance Tracker with users' bank accounts and credit cards.
4. Add features for tracking investments, including stocks, mutual funds, and other assets.

## FAQ

### Q: How do I add an expense or income?

A: You can add an expense or income by logging in and using the respective forms in the application.

### Q: How do I generate a financial report?

A: You can generate a financial report from the Reports section in the application.
