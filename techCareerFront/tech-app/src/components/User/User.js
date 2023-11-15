import React from 'react'
import { useParams } from 'react-router-dom'
import Add from '../Bmi/Add';

// Bu React bileşeni, kullanıcının profil sayfasını temsil eder ve kullanıcının kimliğini (userId) alarak bu 
// bilgiyle ilişkilendirilmiş Add bileşenini çağırır.

function User() {
  const {userId} = useParams();  

  return (
    <div>
      User!!! {userId}
      <Add userId={userId}></Add>
    </div>
  )
}

export default User;
