import { Link } from "react-router-dom";

const HomePage = () => {
  return (
    <div>
      <h1>Workout Tracker</h1>
      <Link to="/auth">login...</Link>
    </div>
  );
};

export default HomePage;
