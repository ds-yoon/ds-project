function [ MaxError ] = Chebyshev(n)
%
% This function evaluate a Chebyshev representation of polynomial that
% interpolates n points
%
% Input :     n - number of Chebyshev points within the given interval
%
% Output : plot - The graph of the interpolating polynomial of true value, and
%                 approximated values. 
%      MaxError - Error between the true value x data vs approximated value
%                 x data
%
a = -1;
b = 1;
i = 0:1:n-1;
x = (a+b)/2-(a-b)*cos((2*i+1)*pi/(2*n))/2;
f = (1+25*x.^2).^-1;
p = polyfit(x, f, n - 1);
x1 = linspace(a,b, 201);
f1 = (1+25*x1.^2).^-1;
pvals = polyval(p, x1);
hold on;
plot(x, f, 'ro') % data points
plot(x1, f1, 'k--') % true graph
plot(x1, pvals, 'b') % approximation graph
MaxError = max(abs(pvals - f1));
title('Interpolation using Chebyshev points.');
legend('Interpolation points', 'True function', 'Interpolating poly');
hold off
end