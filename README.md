# Citronix

Le projet Citronix consiste à développer une application de gestion pour une ferme de citrons, permettant aux fermiers de suivre la production, la récolte, et la vente de leurs produits. L'application doit faciliter la gestion des fermes, champs, arbres, récoltes, et ventes, tout en optimisant le suivi de la productivité des arbres en fonction de leur âge.

## Installation

Pour installer l'application, il suffit de cloner le projet depuis GitHub et de se placer dans le répertoire du projet.

```bash
git clone https://github.com/HMZElidrissi/citronix.git
cd citronix
mvn spring-boot:run
```

L'application est ensuite accessible à l'adresse suivante : [http://localhost:8084](http://localhost:8084)

## Fonctionnalités

### 1. Gestion des Fermes
- Création et modification des fermes
- Suivi des informations (nom, localisation, superficie)
- Recherche multicritère avec Criteria Builder

### 2. Gestion des Champs
- Association des champs aux fermes
- Gestion des superficies avec validation
- Limite de 10 champs maximum par ferme

### 3. Gestion des Arbres
- Suivi des plantations avec dates
- Calcul automatique de l'âge
- Estimation de la productivité selon l'âge:
    - Jeune (< 3 ans): 2,5 kg/saison
    - Mature (3-10 ans): 12 kg/saison
    - Vieux (> 10 ans): 20 kg/saison

### 4. Gestion des Récoltes
- Suivi saisonnier (hiver, printemps, été, automne)
- Une récolte par saison
- Enregistrement des quantités récoltées

### 5. Gestion des Ventes
- Enregistrement des transactions
- Calcul automatique des revenus
