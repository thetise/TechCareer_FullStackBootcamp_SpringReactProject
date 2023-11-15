import React, { useState } from 'react';

// Bu React bileşeni, kullanıcının kilo ve boy bilgilerini girerek vücut kitle endeksini hesaplayabileceği bir arayüz sağlar.

function Add(props){
  const [weight, setWeight] = useState('');
  const [height, setHeight] = useState('');
  const {userId} = props;

 const saveProduct = () => {
    fetch("/bmis", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            Authorization: localStorage.getItem("tokenKey"),
        },
        body: JSON.stringify({
            height: height,
            userId: userId,
            weight: weight
        }),
    })
    .then((res) => res.json())
    .catch((err) => console.log(err))
 }

 const handleSubmit = () => {
    saveProduct();
    setWeight("")
    setHeight("")
    window.location.reload(true)
 }

 const handleHeight = (value) => {
    setWeight(value);
 }

 const handleWeight = (value) => {
    setHeight(value);
 }


  return (
    
        <div className="bg-blue-500 p-6 rounded-lg shadow-md w-1/4">
          <h2 className="text-white text-2xl mb-4">Vücut Kitle Endeksi Hesapla</h2>
          <form onSubmit={saveProduct}>
            <div className="mb-4">
              <label className="block text-white">Kilo</label>
              <input
                type="number"
                value={weight}
                onChange={(e) => handleHeight(e.target.value)}
                className="block w-full p-2 rounded-lg bg-blue-100 text-gray-800"
                required
              />
            </div>
            <div className="mb-4">
              <label className="block text-white">Boy:</label>
              <input
                type="number"
                value={height}
                onChange={(e) => handleWeight(e.target.value)}
                className="block w-full p-2 rounded-lg bg-blue-100 text-gray-800"
                required
              />
            </div>
            <button
              onClick={handleSubmit}
              className="bg-white text-blue-500 font-semibold py-2 px-4 rounded-lg"
            >
              Hesapla
            </button>
          </form>
        </div>
  );
};

export default Add;