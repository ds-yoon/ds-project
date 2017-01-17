function [ MaxError ] = EquallySpaced(n)
%
% This function evaluate a polynomial that interpolates n points that are
% equally spaced. 
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
x = linspace(a, b, n);
f = (1+25*x.^2).^-1;
p = polyfit(x, f, n - 1);
x1 = linspace(a, b, 201);
f1 = (1+25*x1.^2).^-1;
pvals = polyval(p, x1);
hold on;
plot(x, f, 'ro') % points 
plot(x1, f1, 'k--') % true graph
plot(x1, pvals, 'b') % approximation graph
MaxError = max(abs(pvals - f1));
title('Interpolation using equally spaced points.');
legend('Interpolation points', 'True function', 'Interpolating poly');
hold off;
end