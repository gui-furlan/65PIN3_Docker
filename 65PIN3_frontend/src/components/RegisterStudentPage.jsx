import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { api } from "../api/apiService";
import "../css/RegisterStudentPage.css";

const RegisterStudentPage = () => {
    const [formData, setFormData] = useState({
      password: '',
      nome: '',
      sobrenome: '',
      cpf: '',
      anoInicio: '',
      semestreInicio: '',
      cursoId: '' // Campo adicional para o curso selecionado
    });
    const [cursos, setCursos] = useState([]); // Lista de cursos
    const navigate = useNavigate();
  
    // Carregar cursos do endpoint
    useEffect(() => {
      const fetchCursos = async () => {
        try {
          const data = await api.getCursos(); // Chama o método centralizado na API
          setCursos(data);
        } catch (error) {
          alert('Erro ao carregar os cursos. Tente novamente mais tarde.');
        }
      };
  
      fetchCursos();
    }, []);
  
    const handleInputChange = (e) => {
      const { name, value } = e.target;
      setFormData({ ...formData, [name]: value });
    };
  
    const handleRegister = async (e) => {
      e.preventDefault();
  
      try {
        const response = await api.registerStudent(formData);
        if (response.ok) {
          alert('Cadastro realizado com sucesso!');
          navigate('/login');
        }
      } catch (error) {
        alert('Erro ao cadastrar estudante. Por favor, tente novamente.');
      }
    };
  
    const handleBack = () => {
      navigate('/login');
    };
  
    return (
      <div className="register-container">
        <form onSubmit={handleRegister} className="register-card">
          <h1 className="register-title">Cadastro de Estudante</h1>
          <input
            type="text"
            name="nome"
            placeholder="Nome"
            className="register-input"
            value={formData.nome}
            onChange={handleInputChange}
          />
          <input
            type="text"
            name="sobrenome"
            placeholder="Sobrenome"
            className="register-input"
            value={formData.sobrenome}
            onChange={handleInputChange}
          />
          <input
            type="text"
            name="cpf"
            placeholder="CPF"
            className="register-input"
            value={formData.cpf}
            onChange={handleInputChange}
          />
          <input
            type="number"
            name="anoInicio"
            placeholder="Ano de Início"
            className="register-input"
            value={formData.anoInicio}
            onChange={handleInputChange}
          />
          <input
            type="number"
            name="semestreInicio"
            placeholder="Semestre de Início"
            className="register-input"
            value={formData.semestreInicio}
            onChange={handleInputChange}
          />
          <input
            type="password"
            name="password"
            placeholder="Senha"
            className="register-input"
            value={formData.password}
            onChange={handleInputChange}
          />
          <select
            name="cursoId"
            className="register-input"
            value={formData.cursoId}
            onChange={handleInputChange}
          >
            <option value="">Selecione um curso</option>
            {cursos.map((curso) => (
              <option key={curso.id} value={curso.id}>
                {curso.titulo}
              </option>
            ))}
          </select>
          <div className="register-buttons">
            <button type="button" className="register-back-button" onClick={handleBack}>
              Voltar
            </button>
            <button type="submit" className="register-submit-button">
              Cadastrar
            </button>
          </div>
        </form>
      </div>
    );
  };
  
  export default RegisterStudentPage;