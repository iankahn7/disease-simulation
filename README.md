# 🧬 Project 4 - Disease Simulation

**Developers:** Wayne Rudnick and Ian Kahn

---

## 📄 Overview

This program simulates the spread of disease using customizable configuration files. The files must follow the specified template/structure as described in the project description. The program:

- Parses the configuration file
- Extracts and applies values associated with each keyword
- Adjusts simulation parameters accordingly

The simulation includes a number of **bonus features** and **extra credit enhancements** for interactivity and realism.

---

## 🎮 Bonus Key Button Specifications

| Key | Action |
|-----|--------|
| `r` | Randomizes positions of all persons |
| `c` | Changes background to a random color |
| `k` | Kills a random dot (if not already dead) |
| `s` | Increases movement speed (distance moved per frame) |
| `w` | Slows down movement speed of all persons |
| `z` | Cures / resurrects all persons and removes immunity |
| `i` | Infects a random person |
| `b` | Enlarges all persons |
| `n` | Shrinks all persons |
| `m` | Plays the default computer beep sound |
| `h` | Reduces the height of the roamable area |
| `y` | Reduces the width of the roamable area |
| `spacebar` | Aligns all persons in a starting line |
| `u` | Updates the scene title to show number of people alive (press repeatedly to refresh) |
| `o` | ⚠️ **WARNING**: Triggers sick face image popups + fake ads |
| `e` | Exits the program |
| `t` | Increases the height of the roamable area |
| `g` | Increases the width of the roamable area |

---

## 💡 Notable Bonus Features

- Each person has **random movement abilities** that update constantly
- A **default system alert sound** plays when a person dies
- **Dynamic sickness scaling** affects:
  - Color
  - Speed
  - Spread probability

---

## 🎨 Color States Guide

| Color | Meaning |
|-------|---------|
| 🟢 Green | Healthy (no afflictions) |
| 🟡 Yellow | Immune |
| 🔴 Red | Sickness level 1 |
| 🟥🟪 Various Reds to Purples | Sickness levels 2–10 (increasingly sick) |
| ⚫ Black | Dead |

> 💡 **Note:** The more purple a person is, the sicker they are. Sicker individuals move slower and are more contagious.

---

## 👥 Project Credits

| Area | Contributor |
|------|-------------|
| Infrastructure / Program Design | **Wayne Rudnick** |
| Input File Argument Handler Logic | **Ian Kahn** |
| GUI Design and Structure | **Wayne Rudnick** |
| README Documentation | **Ian Kahn** |
| Multi-Threading | **Wayne Rudnick** |
| Public Method Documentation / Code Comments | **Ian Kahn** |
| General Program Commenting | **Wayne Rudnick & Ian Kahn** |

---

🧪 Have fun running simulations and experimenting with different parameters and key presses!

