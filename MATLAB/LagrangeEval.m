function pvals = LagrangeEval(w, x_data, f_data, xvals)
%
% This function evaluates the Lagrange form of an interpolating polynomoal
% at xvals.
%
% Input:    w - vector containing weights, as computed by the function
%               LagrangeWeights
%      x_data - vector of length n containing x_i interpolation data
%      f_data - vector of length n containing f_i interpolation data
%       xvals - vector containing a bunch of x-values at which we want to
%               compute p(xvals)
%
% Output: pvals - vector, same shape as xvals, containing p(xvals)
%
a = length(x_data);
b = length(xvals);
pvals = ones(1, a);
for i=1:b
    STOP = false;
    j=1;
    while (STOP == false && j <= a)
        if (xvals(i) == x_data(j))
            th = f_data(j);
            STOP = true;
        else 
            j = j + 1;
        end
    end
    if (STOP == false)
        t = 0;
        m = 0;
        for j=1:a
            t=t + w(j)*f_data(j)/(xvals(i)-x_data(j));
            m = m + w(j)/(xvals(i)-x_data(j));
        end
        th = t/m;
    end
    pvals (1, i) = th;
end
end