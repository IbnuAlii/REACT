import { useState } from 'react';
import './global.css';

function App() {
  const [player1Dice, setPlayer1Dice] = useState(1);
  const [player2Dice, setPlayer2Dice] = useState(1);

  const rollDice = (player) => {
    const newDiceValue = Math.floor(Math.random() * 6) + 1;
    if (player === 1) {
      setPlayer1Dice(newDiceValue);
    } else if (player === 2) {
      setPlayer2Dice(newDiceValue);
    }
  };

  return (
    <div className='container'>
      <div className='players'>
        <div className='player'>
          <h1> Player1 </h1>
          <img className='dice' src={`images/dice${player1Dice}.png`} alt="Player 1 Dice" onClick={() => rollDice(1)}/>
        </div>

        <div className='player'>
          <h1> Player2 </h1>
          <img className='dice' src={`images/dice${player2Dice}.png`} alt="Player 2 Dice" onClick={() => rollDice(2)}/>
        </div>
      </div>
    </div>
  );
}

export default App;
