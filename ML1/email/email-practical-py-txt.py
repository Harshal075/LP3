# %%
import pandas as pd # data processing, CSV file I/O (e.g. pd.read_csv)
from sklearn.model_selection import train_test_split
from sklearn.svm import SVC
from sklearn.metrics import accuracy_score
from sklearn.neighbors import KNeighborsClassifier

# %%
df = pd.read_csv("emails.csv")

# %%
df.head()

# %%
df.isnull().sum()

# %%
X = df.iloc[:,1:3001]
X

# %%
Y = df.iloc[:,-1].values
Y

# %%
train_x,test_x,train_y,test_y = train_test_split(X,Y,test_size=0.25)

# %%
svc = SVC(C=1.0,kernel='rbf',gamma='auto')
# C here is the regularization parameter. Here, L2 penalty is used(default). It is the in
# As C increases, model overfits.
# Kernel here is the radial basis function kernel.
# gamma (only used for rbf kernel) : As gamma increases, model overfits.
svc.fit(train_x,train_y)
y_pred2 = svc.predict(test_x)
print("Accuracy Score for SVC : ", accuracy_score(y_pred2,test_y))

# %%
svc = SVC(C=1.0,kernel='rbf',gamma='auto')
# C here is the regularization parameter. Here, L2 penalty is used(default). It is the in
# As C increases, model overfits.
# Kernel here is the radial basis function kernel.
# gamma (only used for rbf kernel) : As gamma increases, model overfits.
svc.fit(train_x,train_y)
y_pred2 = svc.predict(test_x)
print("Accuracy Score for SVC : ", accuracy_score(y_pred2,test_y))

# %%
X_train, X_test, y_train, y_test = train_test_split(X, Y, test_size = 0.2,random_state=4)

# %%
knn = KNeighborsClassifier(n_neighbors=7)

# %%
knn.fit(X_train, y_train)

# %%
print(knn.predict(X_test))

# %%
print(knn.score(X_test, y_test))

# %%
import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler
from sklearn.neighbors import KNeighborsClassifier
from sklearn.svm import SVC
from sklearn.metrics import accuracy_score, classification_report,confusion_matrix
import matplotlib.pyplot as plt
import seaborn as sns
# Load the dataset
# Make sure the dataset is available in the correct path
emails_df = pd.read_csv('emails.csv')

# %%
 # Split the data into features and target
X = emails_df.drop(columns=['Email No.', 'Prediction']) # Drop unnecessary columns
y = emails_df['Prediction']

# %%
 # Train-test split
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.3,random_state=42)

# %%
# Feature scaling
scaler = StandardScaler()
X_train = scaler.fit_transform(X_train)
X_test = scaler.transform(X_test)

# %%
 # K-Nearest Neighbors (KNN) model
knn = KNeighborsClassifier(n_neighbors=5)
knn.fit(X_train, y_train)

# %%
# Predicting with KNN
y_pred_knn = knn.predict(X_test)

# %%
 # Support Vector Machine (SVM) model
svm = SVC(kernel='linear')
svm.fit(X_train, y_train)

# %%
# Predicting with SVM
y_pred_svm = svm.predict(X_test)

# %%
# Evaluate performance of both models
def evaluate_model(y_test, y_pred, model_name):
    print(f"Performance of {model_name}:\n")
    print("Accuracy:", accuracy_score(y_test, y_pred))
    print("Classification Report:\n", classification_report(y_test, y_pred))
    print("Confusion Matrix:\n", confusion_matrix(y_test, y_pred))
# Plot confusion matrix
    cm = confusion_matrix(y_test, y_pred)
    plt.figure(figsize=(5, 4))
    sns.heatmap(cm, annot=True, fmt='d', cmap='Blues', xticklabels=['Not Spam','Spam'], yticklabels=['Not Spam', 'Spam'])
    plt.title(f"Confusion Matrix for {model_name}")
    plt.ylabel('True label')
    plt.xlabel('Predicted label')
    plt.show()

# %%
 # Evaluate KNN model
evaluate_model(y_test, y_pred_knn, "K-Nearest Neighbors")

# %%
 # Evaluate SVM model
evaluate_model(y_test, y_pred_svm, "Support Vector Machine")

# %%
 # Compare accuracy scores
print("K-Nearest Neighbors Accuracy:", accuracy_score(y_test, y_pred_knn))
print("Support Vector Machine Accuracy:", accuracy_score(y_test, y_pred_svm))


