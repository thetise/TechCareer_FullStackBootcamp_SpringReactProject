import React from 'react'

// Bu React bileşeni, vücut kitle endeksi (BMI) bilgilerini gösteren bir arayüz sağlar.

function Bmi(props) {

  const {bodyMassIndexValue, result, weight, height, id} = props;

  return(
        <div className="container mx-auto">
            <div className="bg-white p-4 border rounded shadow-md flex justify-center">
              <div className='flex items-center justify-between w-1/2'>
              <div className='bg-orange-100'>
                  <h3 className="text-xl font-semibold"> {id}- </h3>
                </div>
                <div className='bg-green-100'>
                  <h3 className="text-xl font-semibold"> Vücut Kitle İndeksi: {bodyMassIndexValue}</h3>
                </div>
                <div className='bg-blue-100'>
                  <h3 className="text-xl font-semibold">Boy: {height}</h3>
                </div>
                <div className='bg-red-100'>
                  <h3 className="text-xl font-semibold">Kilo: {weight}</h3>
                </div>
                <div className='bg-yellow-100'>
                  <h3 className="text-xl font-semibold">Sonuç: {result}</h3>
                </div>
              </div>
            </div>
        </div>
  )
}

export default Bmi;
