const BASE_URL = 'http://127.0.0.1:8080/api';

export const api = {
  login: async (cpf, senha) => {
    const response = await fetch(`${BASE_URL}/login`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ cpf, senha }),
    });

    if (!response.ok) {
      throw new Error('Erro ao fazer login.');
    }

    return await response.json(); // Retorna o objeto completo
  },

  // Método para registrar estudante
  registerStudent: async (studentData) => {
    const response = await fetch(`${BASE_URL}/usuarios/estudantes`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(studentData),
    });

    if (!response.ok) {
      throw new Error('Erro ao cadastrar estudante.');
    }

    return response; // Retorna a resposta para verificação
  },

  getCursos: async () => {
    const response = await fetch(`${BASE_URL}/cursos`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
      },
    });

    if (!response.ok) {
      throw new Error('Erro ao buscar cursos.');
    }

    return await response.json();
  },

  getModalidades: async () => {
    const response = await fetch(`${BASE_URL}/tipos-atividade`);
    if (!response.ok) {
      throw new Error('Erro ao buscar modalidades.');
    }
    return await response.json();
  },

  registerActivity: async (activityData) => {
    const response = await fetch(`${BASE_URL}/atividades`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(activityData),
    });

    if (!response.ok) {
      throw new Error('Erro ao cadastrar atividade.');
    }
    return response;
  },

  getAtividadesByEstudante: async (estudanteId) => {
    const response = await fetch(`${BASE_URL}/atividades/estudante/${estudanteId}`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
      },
    });

    if (!response.ok) {
      throw new Error('Erro ao buscar atividades do estudante.');
    }

    return await response.json();
  },

  getResumoByEstudante: async (estudanteId) => {
    const response = await fetch(`${BASE_URL}/atividades/resumo/estudante/${estudanteId}`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
      },
    });

    if (!response.ok) {
      throw new Error('Erro ao buscar o resumo do estudante.');
    }

    return await response.json();
  },

};
