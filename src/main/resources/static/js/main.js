document.addEventListener('DOMContentLoaded', () => {
    const messageContainer = document.getElementById('messageContainer');
    const deckDots = document.getElementById('deckDots');
    const addBtn = document.getElementById('addMessage');
    const prevBtn = document.getElementById('prevCard');
    const nextBtn = document.getElementById('nextCard');
    const statMessages = document.getElementById('statMessages');
    const statDegree = document.getElementById('statDegree');
    const flowSteps = document.querySelectorAll('.flow-step');
    const encryptForm = document.getElementById('encryptForm');
    const loadingOverlay = document.getElementById('loadingOverlay');

    let currentIndex = 0;

    // --- Stats & Flow Engine ---
    function updateStats() {
        const cards = document.querySelectorAll('.message-card');
        if (statMessages) statMessages.textContent = cards.length;
        if (statDegree) statDegree.textContent = Math.max(0, cards.length - 1);
    }

    // Keep backend flow lock intact (Only Step 1: Plaintext is active on frontend)
    function lockFlowToFrontendState() {
        flowSteps.forEach((step, idx) => {
            if (idx === 0) {
                step.classList.add('active'); // Plaintext active
            } else {
                step.classList.remove('active'); // Backend steps stay dim
            }
        });
    }

    // --- Deck Dots Sync ---
    function updateDots() {
        if (!deckDots) return;
        const cards = document.querySelectorAll('.message-card');
        deckDots.innerHTML = '';
        cards.forEach((_, idx) => {
            const dot = document.createElement('span');
            dot.className = `dot ${idx === currentIndex ? 'active' : ''}`;
            dot.addEventListener('click', () => goToCard(idx));
            deckDots.appendChild(dot);
        });
    }

    // --- Card Carousel Navigation ---
    function updateCarousel() {
        const cards = document.querySelectorAll('.message-card');
        cards.forEach((card, idx) => {
            card.classList.remove('active', 'left', 'right');
            if (idx === currentIndex) {
                card.classList.add('active');
            } else if (idx < currentIndex) {
                card.classList.add('left');
            } else {
                card.classList.add('right');
            }
        });
        updateDots();
    }

    function goToCard(index) {
        const cards = document.querySelectorAll('.message-card');
        if (index >= 0 && index < cards.length) {
            currentIndex = index;
            updateCarousel();
        }
    }

    // --- Button Event Handlers ---
    if (prevBtn) {
        prevBtn.addEventListener('click', () => {
            if (currentIndex > 0) goToCard(currentIndex - 1);
        });
    }

    if (nextBtn) {
        nextBtn.addEventListener('click', () => {
            const cards = document.querySelectorAll('.message-card');
            if (currentIndex < cards.length - 1) goToCard(currentIndex + 1);
        });
    }

    // --- Add Card Action ---
    if (addBtn) {
        addBtn.addEventListener('click', () => {
            const cards = document.querySelectorAll('.message-card');
            const newIndex = cards.length + 1;

            const newCard = document.createElement('div');
            newCard.className = 'message-card right';
            newCard.innerHTML = `
                <div class="card-header">
                    <span class="message-title">Message ${String(newIndex).padStart(2, '0')}</span>
                    <button type="button" class="delete-btn">✕</button>
                </div>
                <div class="input-group message-group">
                    <label>Message</label>
                    <textarea name="message" placeholder="Write your hidden message..." required></textarea>
                </div>
                <div class="input-group key-group">
                    <label>Key</label>
                    <input type="text" name="key" placeholder="Secret key..." required>
                </div>
            `;

            messageContainer.appendChild(newCard);
            attachDeleteHandler(newCard);

            // Shift focus to the newly created card
            currentIndex = cards.length;
            updateCarousel();
            updateStats();
            lockFlowToFrontendState();
        });
    }

    // --- Delete Card Action ---
    function attachDeleteHandler(card) {
        const deleteBtn = card.querySelector('.delete-btn');
        if (!deleteBtn) return;

        deleteBtn.addEventListener('click', (e) => {
            e.stopPropagation();
            const cards = document.querySelectorAll('.message-card');
            if (cards.length <= 1) {
                alert('You must retain at least one message.');
                return;
            }

            card.remove();

            // Recalculate card index bounds
            const remainingCards = document.querySelectorAll('.message-card');
            if (currentIndex >= remainingCards.length) {
                currentIndex = remainingCards.length - 1;
            }

            updateCarousel();
            updateStats();
            lockFlowToFrontendState();
        });
    }

    // Attach delete listeners to existing cards on initialization
    document.querySelectorAll('.message-card').forEach(attachDeleteHandler);

    // --- Submit Form & Loader Trigger ---
    if (encryptForm && loadingOverlay) {
        encryptForm.addEventListener('submit', () => {
            loadingOverlay.classList.add('show');
        });
    }

    // --- Cipher Output Clipboard & Download Actions ---
    const copyBtn = document.getElementById('copyCipher');
    const downloadBtn = document.getElementById('downloadCipher');
    const clearBtn = document.getElementById('clearCipher');
    const cipherTextArea = document.getElementById('cipherText');

    if (copyBtn && cipherTextArea) {
        copyBtn.addEventListener('click', () => {
            navigator.clipboard.writeText(cipherTextArea.value).then(() => {
                const orig = copyBtn.textContent;
                copyBtn.textContent = '✓ Copied!';
                setTimeout(() => copyBtn.textContent = orig, 2000);
            });
        });
    }

    if (downloadBtn && cipherTextArea) {
        downloadBtn.addEventListener('click', () => {
            const blob = new Blob([cipherTextArea.value], { type: 'text/plain' });
            const url = URL.createObjectURL(blob);
            const a = document.createElement('a');
            a.href = url;
            a.download = 'polycrypt-ciphertext.txt';
            a.click();
            URL.revokeObjectURL(url);
        });
    }

    if (clearBtn && cipherTextArea) {
        clearBtn.addEventListener('click', () => {
            cipherTextArea.value = '';
            const section = document.querySelector('.cipher-output');
            if (section) section.style.display = 'none';
        });
    }

    // --- Initialize View On Load ---
    updateCarousel();
    updateStats();
    lockFlowToFrontendState();
});