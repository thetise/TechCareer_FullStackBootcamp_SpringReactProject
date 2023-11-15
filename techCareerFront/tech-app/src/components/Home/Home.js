import React from 'react'
import Bmi from '../Bmi/Bmi';
import { useState, useEffect } from 'react';

// Bu React bileşeni, sunucudan alınan vücut kitle endeksi (BMI) bilgilerini listelemek ve 
// kullanıcıya filtreleme seçeneği sunmak için kullanılır.

function Home() {
  
  const [error, setError] = useState(null);
  const [isLoaded, setIsLoaded] = useState(false);
  const [productList, setProductList] = useState([]);
  const [filterText, setFilterText] = useState("");

  const filtered = productList.filter((item) => {
    return Object.keys(item).some((key) => 
        item[key].toString().toLowerCase().includes(filterText.toLocaleLowerCase())
    );
  });
  
  useEffect(() => {
    fetch("/bmis")
    .then(res => res.json())
    .then(
        (result) => {
            setIsLoaded(true);
            setProductList(result);
        },
        (error) => {
            setIsLoaded(true);
            setError(error);
        }
    )
  }, [])

  if(error){
    return <div>Error !!!</div>
  }else if(!isLoaded){
    return <div>Loading...</div>
  }else{
    return(
        <div className='flex flex-col items-center'>
            <input
                className='bg-gray-200 rounded-lg border-2 border-black h-12 text-black text-3xl'
                placeholder='Filtrele'
                value={filterText}
                onChange={(e) => setFilterText(e.target.value)}
            />
            {filtered.map((bmis, index) => (
                <Bmi key = {index} userName = {bmis.userName} height = {bmis.height} id = {bmis.id}
                weight = {bmis.weight} bodyMassIndexValue = {bmis.bodyMassIndexValue} result = {bmis.result}></Bmi>
            ))}
        </div>      
    );
  }  
}

export default Home
