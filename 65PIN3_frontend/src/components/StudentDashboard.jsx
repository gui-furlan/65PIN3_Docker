import React, { useContext, useEffect, useState } from 'react';
import Layout from '../layouts/Layout';
import { Navigate, useNavigate } from 'react-router-dom';
import { AuthContext } from '../context/AuthContext';
import { api } from '../api/apiService';
import '../css/StudentDashboard.css';

const StudentDashboard = () => {
  const { session } = useContext(AuthContext);
  const [atividades, setAtividades] = useState([]);
  const [resumo, setResumo] = useState(null);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchAtividades = async () => {
      try {
        setLoading(true);

        const resumoData = await api.getResumoByEstudante(session?.estudante?.id);
        setResumo(resumoData);

        const data = await api.getAtividadesByEstudante(session?.estudante?.id);
        setAtividades(data);
        
      } catch (error) {
        alert('Erro ao carregar as atividades. Tente novamente mais tarde.');
      } finally {
        setLoading(false);
      }
    };

    fetchAtividades();
  }, [session?.estudante?.id]);

  if (loading) {
    return <p>Carregando...</p>;
  }

  const handleNewActivity = () => {
    navigate('/atividades/cadastrar'); // Redireciona para a página de cadastro de atividade
  };

  return (
    <Layout>
      <div>
        <h1>Seu progresso</h1>
        <div className="progress-summary">
            {resumo ? (
              <>
                <p>
                  <strong>Créditos validados:</strong> {resumo.creditosValidados.toFixed(2)}
                </p>
                <p>
                  <strong>Créditos pendentes:</strong> {resumo.creditosPendentes.toFixed(2)}
                </p>
                <p>
                  <strong>Créditos faltantes:</strong> {resumo.creditosFaltantes.toFixed(2)}
                </p>
              </>
            ) : (
              <p>Resumo não disponível.</p>
            )}
          </div>
        <h2>Suas atividades</h2>
        <button className="new-activity-button" onClick={handleNewActivity}>
            + Nova atividade
          </button>
          <div className="dashboard-activities">
          {atividades.length > 0 ? (
            atividades.map((atividade) => (
              <div key={atividade.id} className="activity-card">
                <h2>{atividade.titulo}</h2>
                <p>{atividade.descricao}</p>
                <span className={`status ${atividade.status.toLowerCase()}`}>
                  {atividade.status}
                </span>
              </div>
            ))
          ) : (
            <p>Você ainda não possui atividades cadastradas.</p>
          )}
        </div>
      </div>
    </Layout>
  );
};

export default StudentDashboard;
