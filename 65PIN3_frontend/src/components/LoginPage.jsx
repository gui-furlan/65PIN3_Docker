import React, { useState, useContext } from 'react';
import { AuthContext } from '../context/AuthContext.jsx';
import { useNavigate, Link } from 'react-router-dom';
import { api } from '../api/apiService.js'
import '../css/LoginPage.css';

const LoginPage = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const { login } = useContext(AuthContext);
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      const data = await api.login(username, password); // Supondo que a API retorne o objeto completo
      console.log(data);
      login(data); // Salva o objeto na sessão
      navigate('/dashboard');
    } catch (error) {
      alert('Erro ao fazer login. Verifique suas credenciais.');
    }
  };

  return (
    <div className="login-container">
      <form onSubmit={handleLogin} className="login-card">
        <h1 className="login-title">Bem vindo!</h1>
        <input
          type="text"
          placeholder="Usuário"
          className="login-input"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
        <input
          type="password"
          placeholder="Senha"
          className="login-input"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <button type="submit" className="login-button">Entrar</button>
        <p className="login-link">
          Não tem uma conta? <Link to="/register" className="login-link-text">Cadastre-se aqui.</Link>
        </p>
      </form>
    </div>
  );
};

export default LoginPage;