import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

// Bu React bileşeni, kullanıcı kaydı (register) ve girişi (login) için bir form sunan bir bileşeni temsil eder.

const Register = () => {
  const [userName, setUserName] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  console.log(navigate);

  const handleUsername = (value) => {
    setUserName(value);
  }

  const handlePassword = (value) => {
    setPassword(value);
  }

  const sendRequest = (path) => {
    fetch("/auth/"+path, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body : JSON.stringify({
            userName : userName,
            password : password,
        }),
    })
        .then((res) => res.json())
        .then((result) =>{localStorage.setItem("tokenKey", result.message);
                         localStorage.setItem("currentUser", result.userId);
                         localStorage.setItem("userName", userName)})
        .catch((err) => console.log(err))
  }

  const handleButton = (path) => {
    sendRequest(path);
    console.log('Kullanıcı Adı:', userName);
    console.log('Şifre:', password);
    setUserName("");
    setPassword("");
    if(path === "register"){
      navigate('/auth');
    }else{
      setTimeout(() => {
        navigate('/');
      }, 500);
    
    }
  };

  return (
    <div className="flex flex-col items-center">
      <h2 className="text-2xl font-bold mb-4">Register / Login</h2>
      <div className="mb-4">
        <label className="block mb-1">User Name:</label>
        <input
          type="text"
          className="border border-gray-400 rounded px-4 py-2 w-64"
          value={userName}
          onChange={(e) => handleUsername(e.target.value)}
        />
      </div>
      <div className="mb-4">
        <label className="block mb-1">Password:</label>
        <input
          type="password"
          className="border border-gray-400 rounded px-4 py-2 w-64"
          value={password}
          onChange={(e) => handlePassword(e.target.value)}
        />
      </div>
      <button
        className="bg-blue-500 hover:bg-blue-600 text-white font-semibold py-2 px-24 rounded"
        onClick={() => handleButton("register")}
      >
        Kayıt Ol
      </button>
      <h1 className='m-6'>Are you already registered?</h1>
      <button
        className="bg-green-500 hover:bg-green-600 text-white font-semibold py-2 px-24 rounded"
        onClick={() =>  handleButton("login")}
      >
        Giriş Yap
      </button>
    </div>
  );
};

export default Register;