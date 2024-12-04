import React, { createContext, useState, useEffect } from 'react';

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [session, setSession] = useState(() => {
    const storedSession = sessionStorage.getItem('session');
    return storedSession ? JSON.parse(storedSession) : null;
  });

  const login = (data) => {
    setSession(data);
    sessionStorage.setItem('session', JSON.stringify(data));
  };

  const logout = () => {
    setSession(null);
    sessionStorage.removeItem('session');
  };

  return (
    <AuthContext.Provider value={{ session, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};
