const API_URL = `http://localhost:8080`;
let currentGameSessionId; // Variable to store the current game session ID
let userCredits;

document.addEventListener('DOMContentLoaded', function () {
    const slotMachine = document.getElementById('slot-machine');
    const startButton = document.getElementById('start-btn');
    const cashOutButton = document.getElementById('cash-out-btn');
    const slots = document.querySelectorAll('.slot');
    const again = document.getElementById('again')

    startButton.addEventListener('click', async function () {
        // Call the endpoint to start the game
        const response = await fetch('/game/start', {method: 'POST'});
        const data = await response.json();

        // Store the game session ID
        currentGameSessionId = data.id;
        console.log(currentGameSessionId)



        await simulateRoll();

        // Call the endpoint to get the roll result after the animation
        const rollResponse = await fetch(`/game/results`, {method: 'POST'});
        const rollData = await rollResponse.json();
        console.log(rollData);

        // Call the endpoint to roll slots with the result
        const rollResultsResponse = await fetch(`/game/roll/${currentGameSessionId}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',

            },
            body: JSON.stringify(rollData),
        });
        const rollResults = await rollResultsResponse.json();
        console.log(rollResults);

        // Update the slots with the actual result
        updateSlotsWithResult(...rollData);
        console.log(rollData[0]);
        console.log(rollData[1]);
        console.log(rollData[2]);

        // Update the credits display
        updateCredits(rollResults.credits);
        userCredits = rollResults.credits;


    });

    // startButton.addEventListener('click', async function () {
    //     // Call the endpoint to start the game
    //     const response = await fetch('/game/start', {method: 'POST'});
    //     const data = await response.json();
    //
    //     // Store the game session ID
    //     currentGameSessionId = data.id;
    //     console.log(currentGameSessionId)
    //
    //
    //     await simulateRoll();
    //
    //     // Call the endpoint to get the roll result after the animation
    //     const rollResponse = await fetch(`/game/results`, {method: 'POST'});
    //     const rollData = await rollResponse.json();
    //     console.log(rollData);
    //
    //     // Call the endpoint to roll slots with the result
    //     const rollResultsResponse = await fetch(`/game/roll/${currentGameSessionId}`, {
    //         method: 'POST',
    //         headers: {
    //             'Content-Type': 'application/json',
    //
    //         },
    //         body: JSON.stringify(rollData),
    //     });
    //     const rollResults = await rollResultsResponse.json();
    //     console.log(rollResults);
    //
    //     // Update the slots with the actual result
    //     updateSlotsWithResult(...rollData);
    //     console.log(rollData[0]);
    //     console.log(rollData[1]);
    //     console.log(rollData[2]);
    //
    //     // Update the credits display
    //     updateCredits(rollResults.credits);
    //
    // });

    // again.addEventListener('click', async function () {
    //
    //     const rollResponse = await fetch(`/game/results`, {method: 'POST'});
    //     const rollData = await rollResponse.json();
    //
    //     console.log(currentGameSessionId);
    //     // Call the endpoint to roll slots with the result
    //     const rollResultsResponse = await fetch(`/game/roll/${currentGameSessionId}`, {
    //         method: 'POST',
    //         headers: {
    //             'Content-Type': 'application/json',
    //
    //         },
    //         body: JSON.stringify(rollData),
    //     });
    //     const rollResults = await rollResultsResponse.json();
    //     console.log('Roll Results:', rollResults);
    //
    //
    //     await simulateRoll();
    //
    //
    //     // Update the slots with the actual result
    //     updateSlotsWithResult(...rollData);
    //
    //     // Update the credits display
    //     updateCredits(rollResults.credits);
    // });
    //
    //
    // cashOutButton.addEventListener('click', async function () {
    //     // Call the endpoint to cash out using the stored game session ID
    //     const response = await fetch(`${API_URL}/game/cash-out/${currentGameSessionId}`, {method: 'POST'});
    //     if (response.ok) {
    //         // Handle the response, e.g., move button or make it unclickable
    //         cashOutButton.style.transform = 'translateX(300px)';
    //     } else {
    //         console.error('Cash-out failed:', response.statusText);
    //     }
    // });
    again.addEventListener('click', async function () {

        const rollResponse = await fetch(`/game/results/${userCredits}`, {method: 'POST'});
        const rollData = await rollResponse.json();

        console.log(currentGameSessionId);
        // Call the endpoint to roll slots with the result
        const rollResultsResponse = await fetch(`/game/roll/${currentGameSessionId}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',

            },
            body: JSON.stringify(rollData),
        });
        const rollResults = await rollResultsResponse.json();
        console.log('Roll Results:', rollResults);


        await simulateRoll();


        // Update the slots with the actual result
        updateSlotsWithResult(...rollData);

        // Update the credits display
        updateCredits(rollResults.credits);
    });


    cashOutButton.addEventListener('click', async function () {
        // Call the endpoint to cash out using the stored game session ID
        const response = await fetch(`${API_URL}/game/cash-out/${currentGameSessionId}`, {method: 'POST'});
        if (response.ok) {
            // Handle the response, e.g., move button or make it unclickable
            cashOutButton.style.transform = 'translateX(300px)';
        } else {
            console.error('Cash-out failed:', response.statusText);
        }
    });

    // Other functions...
    async function simulateRoll() {

        slots.forEach(slot => {
            slot.textContent = 'X';
        });

        // Simulate the delay before showing the result
        await new Promise(resolve => setTimeout(resolve, 3000));  // Adjust the delay as needed
    }

    function updateSlotsWithResult(result1, result2, result3) {
        slots[0].textContent = result1;
        slots[1].textContent = result2;
        slots[2].textContent = result3;
        console.log(result1);
    }


    function updateCredits(credits) {
        const creditsDisplay = document.getElementById('credits');
        creditsDisplay.textContent = `Credits: ${credits}`;
    }


});
