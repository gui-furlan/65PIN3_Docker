import React, { useContext } from 'react';
import { Navigate } from 'react-router-dom';
import { AuthContext } from '../context/AuthContext';

const PrivateRoute = ({ children }) => {
  const { session } = useContext(AuthContext);

  console.log(session)

  if (!session) {
    console.log("entrou no if")
    return <Navigate to="/login" />;
  } else {
    console.log("entrou no else")
  }

  return children;
};

export default PrivateRoute;
