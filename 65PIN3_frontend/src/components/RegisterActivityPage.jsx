import React, { useState, useEffect, useContext } from 'react';
import Layout from '../layouts/Layout';
import { api } from '../api/apiService';
import '../css/RegisterActivityPage.css';
import { AuthContext } from '../context/AuthContext';
import { useNavigate } from 'react-router-dom';

const RegisterActivityPage = () => {
  const session = useContext(AuthContext);
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    titulo: '',
    descricao: '',
    tipoAtividadeId: '',
    dataInicio: '',
    dataFinal: '',
    quantidade: '',
    estudanteId: '',
    status: 'Não submetida',
  });
  const [modalidades, setModalidades] = useState([]);
  const [creditosMaximos, setCreditosMaximos] = useState(0);

  // Carregar modalidades ao montar o componente
  useEffect(() => {
    const fetchModalidades = async () => {
      try {
        const data = await api.getModalidades(); // Endpoint para buscar modalidades
        setModalidades(data);
      } catch (error) {
        alert('Erro ao carregar modalidades. Tente novamente mais tarde.');
      }
    };

    fetchModalidades();
  }, []);

  // Atualiza os dados do formulário
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });

    // Calcula os créditos ao alterar quantidade ou modalidade
    if (name === 'quantidade' || name === 'tipoAtividadeId') {
      calculateCreditos(value, formData.tipoAtividadeId || value);
    }
  };

  const calculateCreditos = (quantidade, tipoAtividadeId) => {
    if (quantidade && tipoAtividadeId) {
      const modalidade = modalidades.find((mod) => mod.id === tipoAtividadeId);
      if (modalidade) {
        const creditos = Math.min(
          modalidade.creditosPorQuantidade * parseInt(quantidade, 10),
          modalidade.maximoCreditos
        );
        setCreditosMaximos(creditos);
      }
    }
  };

  const handleRegister = async (e) => {
    e.preventDefault();

    console.log(session);

    formData.estudanteId = session?.session?.estudante?.id || '';

    console.log(formData);
    
    let response = '';

    if (!formData.tipoAtividadeId != '') {
      alert('Selecione uma modalidade!');
    } else {
      try {
        response = await api.registerActivity(formData);
        if (response.ok) {
          alert('Atividade cadastrada com sucesso!');
        }
      } catch (error) {
        alert('Erro ao cadastrar atividade. Por favor, tente novamente.');
      }
    }

    if (response?.ok) {
      navigate('/dashboard');
    }
  };

  const handleCancel = () => {
    navigate('/dashboard');
  };

  return (
    <Layout>
      <div className="register-activity-container">
        <form onSubmit={handleRegister} className="register-activity-card">
          <input
            type="text"
            name="titulo"
            placeholder="Título da atividade..."
            className="register-activity-input-title"
            value={formData.titulo}
            onChange={handleInputChange}
          />
          <textarea
            name="descricao"
            placeholder="Descreva sua atividade..."
            className="register-activity-textarea"
            value={formData.descricao}
            onChange={handleInputChange}
          />
          <select
            name="tipoAtividadeId"
            className="register-activity-select"
            value={formData.tipoAtividadeId}
            onChange={handleInputChange}
          >
            <option value="">Modalidade / disciplina da atividade</option>
            {modalidades.map((modalidade) => (
              <option key={modalidade.id} value={modalidade.id}>
                {modalidade.sigla + " - " + modalidade.nome}
              </option>
            ))}
          </select>
          <select
            name="status"
            className="register-activity-select"
            value={formData.status}
            onChange={handleInputChange}
          >
            <option value="Não submetida">Não submetida</option>
            <option value="Pendente">Pendente</option>
            <option value="Validada">Validada</option>
            <option value="Recusada">Recusada</option>
          </select>
          <div className="register-activity-dates">
            <label>
              Realizei a atividade de: &nbsp;
              <input
                type="date"
                name="dataInicio"
                value={formData.dataInicio}
                onChange={handleInputChange}
              />
            </label>
            <label>
              até: &nbsp;
              <input
                type="date"
                name="dataFinal"
                value={formData.dataFinal}
                onChange={handleInputChange}
              />
            </label>
          </div>
          <div className="register-activity-quantity">
            <label>
              Quantidade: &nbsp;
              <input
                type="number"
                name="quantidade"
                value={formData.quantidade}
                onChange={handleInputChange}
                min="1"
              />
              &nbsp; (horas, semestres, eventos...)
            </label>
            {/* <p>Você pode ganhar até {creditosMaximos} créditos com essa atividade.</p> */}
          </div>
          <div className="register-activity-buttons">
            <button
              type="button"
              className="register-activity-cancel-button"
              onClick={handleCancel}
            >
              Cancelar
            </button>
            <button type="submit" className="register-activity-submit-button">
              Salvar e submeter
            </button>
          </div>
        </form>
      </div>
    </Layout>
  );
};

export default RegisterActivityPage;