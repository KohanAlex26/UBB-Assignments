function f = problem4()
miq = [135; 127; 124; 120; 115; 112; 104; 96; 94; 85];
diq = [121; 131; 112; 115; 99; 118; 106; 89; 92; 90];

%a
scatter(miq,diq)
%b
covariance = cov(miq,diq)
corr_coff =  corrcoef(miq,diq)
%c
%Each mother's daughter has an IQ similar to her mother