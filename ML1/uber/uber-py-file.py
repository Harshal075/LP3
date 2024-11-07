# %%
 # Import necessary libraries
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LinearRegression
from sklearn.ensemble import RandomForestRegressor
from sklearn.metrics import mean_squared_error, r2_score
from scipy import stats
 # Load the dataset
df = pd.read_csv('uber.csv')

# %%
# Display the first few rows of the dataset
df.head()

# %%
 # Convert pickup_datetime to datetime format
df['pickup_datetime'] = pd.to_datetime(df['pickup_datetime'].str.replace(' UTC', ''), format='%Y-%m-%d %H:%M:%S')

# %%
# Feature engineering: Extracting date and time features
df['hour'] = df['pickup_datetime'].dt.hour
df['day_of_week'] = df['pickup_datetime'].dt.dayofweek
df['day_of_month'] = df['pickup_datetime'].dt.day
df['month'] = df['pickup_datetime'].dt.month
df['year'] = df['pickup_datetime'].dt.year

# %%
# Drop the key and datetime columns as they are not needed for modeling
df = df.drop(['key', 'pickup_datetime'], axis=1)

# %%
# Handle missing values if any
df.isnull().sum()

# %%
# Drop rows with missing values (if any)
df = df.dropna()

# %%
# Outlier detection and removal using z-score
z_scores = np.abs(stats.zscore(df[['fare_amount', 'pickup_longitude','pickup_latitude', 'dropoff_longitude', 'dropoff_latitude','passenger_count']]))
df = df[(z_scores < 3).all(axis=1)]

# %%
# Correlation matrix
plt.figure(figsize=(10, 6))
sns.heatmap(df.corr(), annot=True, cmap='coolwarm')
plt.title('Correlation Matrix')
plt.show()

# %%
# Split the data into features (X) and target (y)
X = df.drop('fare_amount', axis=1)
y = df['fare_amount']

# %%
# Split the data into training and test sets
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2,random_state=42)

# %%
# Linear Regression Model
lr_model = LinearRegression()
lr_model.fit(X_train, y_train)

# %%
# Random Forest Regressor Model
rf_model = RandomForestRegressor(n_estimators=100, random_state=42)
rf_model.fit(X_train, y_train)

# %%
# Predictions
y_pred_lr = lr_model.predict(X_test)
y_pred_rf = rf_model.predict(X_test)

# %%
# Evaluate models using R² and RMSE
def evaluate_model(y_test, y_pred):
 r2 = r2_score(y_test, y_pred)
 rmse = np.sqrt(mean_squared_error(y_test, y_pred))
 return r2, rmse

# %%
# Linear Regression Evaluation
r2_lr, rmse_lr = evaluate_model(y_test, y_pred_lr)
print(f"Linear Regression R²: {r2_lr}, RMSE: {rmse_lr}")

# %%
# Random Forest Evaluation
r2_rf, rmse_rf = evaluate_model(y_test, y_pred_rf)
print(f"Random Forest R²: {r2_rf}, RMSE: {rmse_rf}")

# %%
print("\nModel Comparison:")
print(f"Linear Regression- R²: {r2_lr}, RMSE: {rmse_lr}")
print(f"Random Forest- R²: {r2_rf}, RMSE: {rmse_rf}")

# %%
plt.figure(figsize=(10, 6))
plt.scatter(y_test, y_pred_lr, label='Linear Regression', color='blue', alpha=0.6)
plt.scatter(y_test, y_pred_rf, label='Random Forest', color='green', alpha=0.6)
plt.plot([y_test.min(), y_test.max()], [y_test.min(), y_test.max()], 'k--',lw=2)
plt.xlabel('Actual Fare')
plt.ylabel('Predicted Fare')
plt.legend()
plt.title('Actual vs Predicted Fare')
plt.show()


