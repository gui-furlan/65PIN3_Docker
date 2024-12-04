import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import LoginPage from './components/LoginPage';
import StudentDashboard from './components/StudentDashboard';
import RegisterStudentPage from './components/RegisterStudentPage';
import PrivateRoute from './routes/PrivateRoute';
import RegisterActivityPage from './components/RegisterActivityPage';
import { AuthProvider } from './context/AuthContext';

function App() {
  return (
    <AuthProvider>
      <Router>
        <Routes>
          <Route path="/login" element={<LoginPage />} />

          <Route path="/register" element={<RegisterStudentPage />} />

          <Route
            path="/dashboard"
            element={
              <PrivateRoute>
                <StudentDashboard />
              </PrivateRoute>
            }
          />

          <Route
            path="/atividades/cadastrar"
            element={
              <PrivateRoute>
                <RegisterActivityPage />
              </PrivateRoute>
            }
          />
          
          <Route path="*" element={<LoginPage />} />
        </Routes>
      </Router>
    </AuthProvider>
  );
}

export default App;
