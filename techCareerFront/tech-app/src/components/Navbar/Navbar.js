import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import { useNavigate } from 'react-router-dom';

// Bu React bileşeni, uygulama içindeki gezinmeyi sağlamak ve kullanıcı bilgilerine göre belirli işlemleri gerçekleştirmek için kulanılır.

function Navbar() {
  const navigate = useNavigate();
  const [userId, setUserId] = useState();
  const onClick = () => {
    localStorage.removeItem("tokenKey")
    localStorage.removeItem("currentUser")
    localStorage.removeItem("userName")
    navigate(0);

  }
  useEffect(() => {
   const user =  localStorage.getItem("currentUser");
   setUserId(user);
   
  }, [])

  return (
    <div className='h-16 w-full bg-blue-500'>


      <ul className='flex justify-between text-lg p-4'>
        <li><Link to="/">Home</Link></li>
        <li>
          {localStorage.getItem("currentUser") == null ? 
          <Link to="/auth">Login/Register</Link>: 
            <div className='flex'>
              <div onClick = {onClick}><h1 style={{cursor:'pointer'}}>Exit /</h1></div>
              <Link to={{pathname : "/users/" + localStorage.getItem("currentUser")}}>/ Profile</Link>
            </div>}
        </li>
      </ul>
    </div>
  )
}

export default Navbar
