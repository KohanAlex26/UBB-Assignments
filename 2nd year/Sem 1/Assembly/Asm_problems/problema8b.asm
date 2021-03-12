bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit,scanf,printf,gets
import scanf msvcrt.dll
import printf msvcrt.dll
import gets msvcrt.dll              
; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
    s resb 20 
    d resb 20 
    format db '%d    ',0
    format_sir db '%s', 0 
    citireMesaj db 'Citeste sir: ',0
    printareMesaj db 'Printare sir: ',0
; our code starts here
segment code use32 class=code
    start:
        ; ...
    ;2. Read a string. Extract all small letters and save into second string. Print the second string on screen. 
    ;Ex: s = Ana are Mere => d= na are ere sau d= naareere 
         
         ;citire mesaj
    push dword citireMesaj
    call [printf]
    add esp,4*1
    
        ;citire sir
    push dword s
    call [gets] 
    add esp, 4*1 
    
    
        ;rezolvare
    mov ecx, 20 ; nr max de car pe care le putem avea in s
    mov esi, 0 ; pentru a parcurge sirul s
    mov edi, 0 ; pentru a parcurege sirul d
    
    
    repeta:
        mov al, [s+esi] ; accesare elem din sir
        ;push dword esi
        ;push dword format
        ;call [printf]
        ;add esp,4*2
        
        cmp al, 'a'
        JAE continua
        JNAE nucontinua
            continua:
               cmp al,'z'
               JBE adauga
               JNBE nucontinua
            adauga:
               mov [d+edi],al
               inc edi
               inc esi
               jmp end_repeta
            nucontinua:
                inc esi
            
                end_repeta:
    loop repeta
    
        ;printare mesaj
    push dword printareMesaj
    call [printf]
    add esp,4*1
    
        ; afisare
    push dword d
    push dword format_sir
    call [printf]
    add esp, 4*2
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
