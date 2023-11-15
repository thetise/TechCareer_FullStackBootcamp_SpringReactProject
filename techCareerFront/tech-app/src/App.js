import './App.css';
import {BrowserRouter as Router, Routes, Route, BrowserRouter, Switch} from "react-router-dom";
import Auth from './components/Auth/Auth';
//import Product from './components/Product/Product';
import Navbar from './components/Navbar/Navbar';
import User from './components/User/User';
import Home from './components/Home/Home';
import { Navigate} from 'react-router-dom';


function App() {

  return (
    <div className="App">
      <BrowserRouter>
        <Navbar/>
        <Routes>
          <Route exact path='/' element={<Home/>}></Route>
          <Route exact path='/users/:userId' element={<User/>}></Route>
          <Route exact path='/auth' element = {localStorage.getItem("currentUser") != null ? <Navigate to= "/"/>: <Auth/>} ></Route> 
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;