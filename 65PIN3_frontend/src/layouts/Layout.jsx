import React, {useContext} from 'react';
import '../css/Layout.css';
import { AuthContext } from '../context/AuthContext';
import { useNavigate } from 'react-router-dom';

const Layout = ({ children }) => {
  const { session, logout } = useContext(AuthContext);
  const navigate = useNavigate();

  const handleLogout = () => {
    logout(); // Remove a sessão
    navigate('/login'); // Redireciona para a tela de login
  };

  return (
    <div className="layout-container">
      <aside className="sidebar">
        <div className="sidebar-profile">
          <p className="profile-name">{session?.estudante?.nome}</p>
          <p className="profile-course">{session?.estudante?.curso?.titulo}</p>
        </div>
        <nav className="sidebar-menu">
        </nav>
        <div className="sidebar-footer">
          <button className="logout-button" onClick={handleLogout}>
            Sair
          </button>
        </div>
      </aside>
      <main className="content">{children}</main>
    </div>
  );
};

export default Layout;
