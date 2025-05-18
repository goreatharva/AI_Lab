% Expert System for Hospital Diagnosis

start :-
    write('--- Welcome to the Hospital Expert System ---'), nl,
    clear_knowledge_base,
    diagnosis(Disease),
    nl,
    write('Based on your symptoms, you may be suffering from: '), write(Disease), nl,
    treatment(Disease),
    nl,
    clear_knowledge_base.

start :-
    nl,
    write('We were unable to diagnose your disease.'), nl,
    write('Please consult your doctor as soon as possible.'), nl,
    clear_knowledge_base.

% Disease rules
diagnosis(flu) :-
    has_symptom(fever),
    has_symptom(headache),
    has_symptom(chills),
    has_symptom(body_ache).

diagnosis(typhoid) :-
    has_symptom(fever),
    has_symptom(abdominal_pain),
    has_symptom(loss_of_appetite),
    has_symptom(weakness).

diagnosis(covid19) :-
    has_symptom(fever),
    has_symptom(cough),
    has_symptom(breathing_difficulty),
    has_symptom(loss_of_taste_or_smell).

diagnosis(malaria) :-
    has_symptom(fever),
    has_symptom(chills),
    has_symptom(sweating),
    has_symptom(nausea).

diagnosis(diabetes) :-
    has_symptom(frequent_urination),
    has_symptom(increased_thirst),
    has_symptom(weight_loss),
    has_symptom(fatigue).

diagnosis(hypertension) :-
    has_symptom(high_blood_pressure),
    has_symptom(headache),
    has_symptom(dizziness),
    has_symptom(blurry_vision).

diagnosis(dengue) :-
    has_symptom(fever),
    has_symptom(rashes),
    has_symptom(joint_pain),
    has_symptom(nausea).

% Treatments
treatment(flu) :-
    write('Recommended: Rest, drink fluids, take paracetamol, consult doctor if symptoms persist.').

treatment(typhoid) :-
    write('Recommended: Antibiotics, hydration, and medical supervision.').

treatment(covid19) :-
    write('Recommended: Isolate, rest, monitor oxygen levels, consult a physician.').

treatment(malaria) :-
    write('Recommended: Antimalarial drugs and follow-up care.').

treatment(diabetes) :-
    write('Recommended: Monitor blood sugar, balanced diet, regular medication.').

treatment(hypertension) :-
    write('Recommended: Reduce salt, stress management, exercise, prescribed medication.').

treatment(dengue) :-
    write('Recommended: Maintain hydration, avoid painkillers like aspirin, rest, consult doctor.').

% Symptom handling
has_symptom(Symptom) :-
    known(yes, Symptom), !.

has_symptom(Symptom) :-
    known(no, Symptom), !, fail.

has_symptom(Symptom) :-
    ask(Symptom).

ask(Symptom) :-
    write('Do you have the following symptom: '), write(Symptom), write('? (yes/no): '),
    read(Response),
    nl,
    ( (Response == yes ; Response == y)
      -> assertz(known(yes, Symptom))
      ;  assertz(known(no, Symptom)), fail).

% Clear memory
clear_knowledge_base :-
    retract(known(_, _)), fail.
clear_knowledge_base.

% Dynamic memory
:- dynamic known/2.
