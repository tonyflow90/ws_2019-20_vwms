import {
    html,
    render
} from '../node_modules/lit-html/lit-html.js';

let WINNING_PLAYER = ''
const HUMAN_PLAYER = 'O';
const COMPUTER_PLAYER = 'X';
const WINNING_COMBINATIONS = [
    [0, 3, 6],
    [1, 4, 7],
    [2, 5, 8],
    [0, 1, 2],
    [3, 4, 5],
    [6, 7, 8],
    [0, 4, 8],
    [2, 4, 6]
]

class TictactoeApp extends HTMLElement {
    constructor() {
        super();
        this._shadowRoot = this.attachShadow({
            'mode': 'open'
        });
        render(this.template(), this._shadowRoot, {
            eventContext: this
        });
    }

    template() {
        return html `
                <style>
                :host {
                    display: block;
                }

                table {
                    /* width:500px;
                    height: 500px; */
                    border: 2px solid black;
                    border-collapse: collapse;
                }

                td {
                    width: 50px;
                    height: 50px;
                    border: 2px solid black;
                    border-collapse: collapse;
                }
                </style>
                <div>
                    <h1>Tic Tac Toe App</h1>

                    <button @click="${this.resetGame}">Reset</button>

                    <table>
                        <tbody>
                            <tr><!-- erste Zeile -->
                                <td class="cell" id="0" @click="${this.setHumanPlayerMark}"></td>
                                <td class="cell" id="1" @click="${this.setHumanPlayerMark}"></td>
                                <td class="cell" id="2" @click="${this.setHumanPlayerMark}"></td>
                            </tr>
                            <tr><!-- zweite Zeile -->
                                <td class="cell" id="3" @click="${this.setHumanPlayerMark}"></td>
                                <td class="cell" id="4" @click="${this.setHumanPlayerMark}"></td>
                                <td class="cell" id="5" @click="${this.setHumanPlayerMark}"></td>
                            </tr>
                            <tr><!-- dritte Zeile -->
                                <td class="cell" id="6" @click="${this.setHumanPlayerMark}"></td>
                                <td class="cell" id="7" @click="${this.setHumanPlayerMark}"></td>
                                <td class="cell" id="8" @click="${this.setHumanPlayerMark}"></td>
                            </tr>
                        </tbody>
                    </table>

                    <dialog id="lostDialog">
                        <form>
                            <section>
                                <p>YOU LOST!!!</p>
                            </section>
                            <menu>
                               <button @click="${this.startGame}">Start Game</button>
                            </menu>
                        </form>
                    </dialog>
                    <dialog id="winDialog">
                        <form>
                            <section>
                                <p>YOU WON!!!</p>
                            </section>
                            <menu>
                               <button @click="${this.startGame}">Start Game</button>
                            </menu>
                        </form>
                    </dialog>
                </div>
        `;
    }

    restartGame() {
        this._shadowRoot.querySelector('#lostDialog').close();
        this._shadowRoot.querySelector('#winDialog').close();
        this.resetGame();
    }

    resetGame() {
        WINNING_PLAYER = ''
        this._shadowRoot.querySelectorAll('.cell').forEach(cell => {
            cell.innerHTML = '';
        });
    }

    startGame() {
        this.checkForWinningCombinations();
    }

    setHumanPlayerMark(oEvent) {
        this._setPlayerMark(oEvent.currentTarget, HUMAN_PLAYER);
        if (this.checkGameState())
            return;
        this.setComputerPlayerMark();
    }

    setComputerPlayerMark() {
        this._setPlayerMark(this._getRandomEmptyTableCell(), COMPUTER_PLAYER);
        this.checkGameState();
    }

    _getEmptyTableCells() {
        return Array.from(this._shadowRoot.querySelectorAll('.cell')).filter(cell => cell.innerText === '')
    }

    _getRandomEmptyTableCell() {
        let emptyTableCells = this._getEmptyTableCells();
        let randomTableCellId = Math.ceil(Math.random() * emptyTableCells.length - 1);
        let emptyTableCell = emptyTableCells[randomTableCellId];
        return emptyTableCell;
    }

    _setPlayerMark(tablecell, mark) {
        if (tablecell.innerText === '')
            tablecell.innerText = mark;
    }

    checkGameState() {
        this.checkWinningCombinations();
        if (WINNING_PLAYER === HUMAN_PLAYER) {
            this._shadowRoot.querySelector('#winDialog').showModal();
        } else if (WINNING_PLAYER === COMPUTER_PLAYER) {
            this._shadowRoot.querySelector('#lostDialog').showModal();
        }
        return WINNING_PLAYER;
    }

    checkWinningCombinations() {
        // check if one winning combination is met   
        WINNING_COMBINATIONS.forEach(combination => {
            if (this._shadowRoot.getElementById(combination[0]).innerText === COMPUTER_PLAYER &&
                this._shadowRoot.getElementById(combination[1]).innerText === COMPUTER_PLAYER &&
                this._shadowRoot.getElementById(combination[2]).innerText === COMPUTER_PLAYER) {
                WINNING_PLAYER = COMPUTER_PLAYER;
            } else if (this._shadowRoot.getElementById(combination[0]).innerText === HUMAN_PLAYER &&
                this._shadowRoot.getElementById(combination[1]).innerText === HUMAN_PLAYER &&
                this._shadowRoot.getElementById(combination[2]).innerText === HUMAN_PLAYER) {
                WINNING_PLAYER = HUMAN_PLAYER;
            }
        });
    }
}

window.customElements.define('tictactoe-app', TictactoeApp);